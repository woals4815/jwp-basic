package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.*;
import next.dao.QuestionDao;

public class HomeController extends AbscrtactController {
    @Override
    public ModalAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        return jspView("home.jsp").addObject("questions", questionDao.findAll());
    }

}
