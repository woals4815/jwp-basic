package next.controller;

import next.http.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
        log.info("DispatcherServlet init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("DispatcherServlet doGet {}", req.getRequestURI());
        String uri = RequestMapping.getController(req.getRequestURI()).execute(req, resp);
        processUri(uri, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("DispatcherServlet doPost {}", req.getRequestURI());
        String uri = RequestMapping.getController(req.getRequestURI()).execute(req, resp);
        processUri(uri, req, resp);
    }

    private void processUri(String uri, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("uri is {}", uri);
        if (uri.startsWith("redirect")) {
            String resource = uri.split(":")[1];
            resp.sendRedirect(resource);
        } else {
            log.debug("direct uri is {}", uri);
            req.getRequestDispatcher(uri).forward(req, resp);
        }
    }
}
