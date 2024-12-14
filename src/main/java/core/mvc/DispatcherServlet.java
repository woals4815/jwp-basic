package core.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.nmvc.AnnotationHandlerMapping;
import core.nmvc.HandlerExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    private LegacyRequestMapping rm;
    private AnnotationHandlerMapping am;

    @Override
    public void init() throws ServletException {

        rm = new LegacyRequestMapping();
        rm.initMapping();
        am = new AnnotationHandlerMapping();
        am.initialize();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();
        logger.debug("Method : {}, Request URI : {}", req.getMethod(), requestUri);
        Object handler = null;
        if (rm.getHandler(req) != null) {
            handler = rm.getHandler(req);
        } else {
            handler = am.getHandler(req);
        }
        ModelAndView mav;
        try {
            if (handler instanceof HandlerExecution) {
                mav = ((HandlerExecution) handler).handle(req, resp);
            } else if (handler instanceof Controller) {
                mav = ((Controller) handler).execute(req, resp);
            } else {
                throw new Exception("No Handler Mapping");
            }
            View view = mav.getView();
            view.render(mav.getModel(), req, resp);
        } catch (Throwable e) {
            logger.error("Exception : {}", e);
            throw new ServletException(e.getMessage());
        }
    }
}
