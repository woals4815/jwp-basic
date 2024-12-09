package next.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

public class UpdateUserController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String userId = request.getParameter("userId");
        User user = DataBase.findUserById(userId);
        switch (uri) {
            case "/users/update":
                if (!UserSessionUtils.isSameUser(request.getSession(), user)) {
                    throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
                }

                User updateUser = new User(request.getParameter("userId"), request.getParameter("password"), request.getParameter("name"),
                        request.getParameter("email"));
                log.debug("Update User : {}", updateUser);
                user.update(updateUser);
                return "redirect:/";
                case "/users/updateForm":
                    if (!UserSessionUtils.isSameUser(request.getSession(), user)) {
                        throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
                    }
                    log.debug("asdfasdf");
                    request.setAttribute("user", user);
                    return "/user/updateForm.jsp";
            default:
                return "redirect:/";

        }
    }

}
