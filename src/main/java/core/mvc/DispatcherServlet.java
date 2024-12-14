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

import core.nmvc.AnnotationHandlerMapping;
import core.nmvc.HandlerExecution;
import core.nmvc.HandlerMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    private LegacyRequestMapping rm;
    private AnnotationHandlerMapping am;

    private List<HandlerMapping> handlerMappings;

    @Override
    public void init() throws ServletException {
        rm = new LegacyRequestMapping();
        am = new AnnotationHandlerMapping();
        rm.initMapping();
        am.initialize();
        handlerMappings.add(rm);
        handlerMappings.add(am);
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
        Object handler = null;
        if (am.getHandler(req) != null) {
            handler = am.getHandler(req);
        } else if(rm.getHandler(req) != null) {
            handler = rm.getHandler(req);
        } else {
            throw new RuntimeException("No Handler");
        }
        return handler;
    }

    private ModelAndView getMav(
            Object handler,
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws Exception {

        if (handler instanceof Controller) {
            return ((Controller) handler).execute(req, resp);
        } else if(handler instanceof HandlerExecution) {
            return ((HandlerExecution) handler).handle(req, resp);
        } else {
            throw new ServletException("No mav");
        }
    }

}
