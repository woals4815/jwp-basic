package next.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 이걸 어디에, 왜 사용해야 하는 거지?
 */
public interface Controller {
    String execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException;
}
