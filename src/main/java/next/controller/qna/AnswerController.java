package next.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.Controller;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.model.Answer;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

public class AnswerController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(AnswerController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = UserSessionUtils.getUserFromSession(req.getSession());
        if (user == null) {
            return "redirect:/users/login";
        }


        Answer newAnswer = new Answer(
                req.getParameter("writer"),
                req.getParameter("contents"),
                Integer.parseInt(req.getParameter("questionId"))
        );

        AnswerDao answerDao = new AnswerDao();
        answerDao.insert(newAnswer);
        ObjectMapper mapper = new ObjectMapper();

        String response = mapper.writeValueAsString(newAnswer);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(response);
        return null;
    }
}
