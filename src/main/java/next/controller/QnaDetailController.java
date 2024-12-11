package next.controller;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QnaDetailController implements Controller {

    private static final Logger log = LoggerFactory.getLogger(QnaDetailController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionId = req.getParameter("questionId");
        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.findById(Integer.parseInt(questionId));
        req.setAttribute("question", question);

        return "/qna/show.jsp";
    }
}
