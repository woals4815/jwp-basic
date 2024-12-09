package next.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForwardController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(ForwardController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/users/loginForm")) {
            return "/user/login.jsp";
        } else if (uri.equals("/users/form")) {
            return "/user/form.jsp";
        }
        return "/index.jsp";
    }
}
