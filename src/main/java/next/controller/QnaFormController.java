package next.controller;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class QnaFormController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (!UserSessionUtils.isLogined(req.getSession())) {
            return "redirect:/users/login";
        }

        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(
                new Question(
                        req.getParameter("writer"),
                        req.getParameter("title"),
                        req.getParameter("contents"),
                        new Timestamp(System.currentTimeMillis()),
                        0
                )
        );
        return "redirect:/";
    }
}
