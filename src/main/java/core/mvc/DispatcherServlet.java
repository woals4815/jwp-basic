package core.mvc;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import core.nmvc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    private LegacyRequestMapping rm;
    private AnnotationHandlerMapping am;

    private List<HandlerMapping> handlerMappings;

    private List<HandlerAdapter> adapters = Lists.newArrayList();

    @Override
    public void init() throws ServletException {
        rm = new LegacyRequestMapping();
        am = new AnnotationHandlerMapping();
        rm.initMapping();
        am.initialize();
        handlerMappings.add(rm);
        handlerMappings.add(am);
        adapters.add(new ControllerHandlerAdapter());
        adapters.add(new HandlerExecutionAdapter());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();
        logger.debug("Method : {}, Request URI : {}", req.getMethod(), requestUri);

        Object handler = this.getHandler(req);
        try {
            ModelAndView mav = this.getMav(handler, req, resp);
            View view = mav.getView();
            view.render(mav.getModel(), req, resp);
        } catch (Throwable e) {
            logger.error("Exception : {}", e);
            throw new ServletException(e.getMessage());
        }
    }

    private Object getHandler(HttpServletRequest  req) {
        for(HandlerMapping hm : handlerMappings) {
            Object handler = hm.getHandler(req);
            if(handler != null) {
                return handler;
            }
        }
        return null;
    }

    private ModelAndView getMav(
            Object handler,
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws Exception {
        for(HandlerAdapter adapter : adapters) {
            if (adapter.supports(handler)) {
                return adapter.handle(handler, req, resp);
            }
        }
        return null;
    }

}
