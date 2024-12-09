package next.web;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/update")
public class UpdateUserFormServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        HttpSession session = req.getSession();

        if (userId == null || userId.isEmpty() || session == null) {
            resp.sendRedirect("/user/login");
            return;
        }

        Object value = session.getAttribute("user");
        if (value == null) {
            resp.sendRedirect("/user/login");
            return;
        }

        User sessionUser = (User) value;
        User existUser = DataBase.findUserById(userId);

        if (!sessionUser.getUserId().equals(existUser.getUserId())) {
            resp.sendRedirect("/");
            return;
        }

        req.setAttribute("user", existUser);
        RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = DataBase.findUserById(userId);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        resp.sendRedirect("/user/list");
    }
}
