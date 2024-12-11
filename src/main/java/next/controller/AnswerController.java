package next.controller;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class AnswerController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = UserSessionUtils.getUserFromSession(req.getSession());
        if (user == null) {
            return "redirect:/users/login";
        }

        Answer newAnswer = new Answer(
                user.getName(),
                req.getParameter("contents"),
                new Timestamp(System.currentTimeMillis()),
                Integer.parseInt(req.getParameter("questionId"))
        );
        AnswerDao answerDao = new AnswerDao();
        answerDao.insert(newAnswer);
        return "redirect:/qna/detail?questionId=" + newAnswer.getQuestionId();
    }
}
