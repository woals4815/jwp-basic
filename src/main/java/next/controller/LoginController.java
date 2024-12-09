package next.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.db.DataBase;
import next.model.User;

public class LoginController implements Controller {
    private static final long serialVersionUID = 1L;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        User user = DataBase.findUserById(userId);

        if (user == null || !user.matchPassword(password)) {
            request.setAttribute("loginFailed", true);
            return "/user/login.jsp";
        }

        HttpSession session = request.getSession();
        session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
        return "redirect:/";
    }
}
