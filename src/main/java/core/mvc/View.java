package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface View {
    public void render(
            Map<String, ?> model,
            HttpServletRequest req,
            HttpServletResponse res
    ) throws Exception;
}
