package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
    ModalAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
