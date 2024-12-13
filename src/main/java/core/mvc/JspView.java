package core.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

public class JspView implements View {
    private String viewName;
    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
    public JspView(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest req, HttpServletResponse res) throws Exception {
        if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
            res.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
            return;
        }
        Set<String> keys = model.keySet();
        for(String key : keys) {
            req.setAttribute(key, model.get(key));
        }
        RequestDispatcher rd = req.getRequestDispatcher(viewName);
        rd.forward(req, res);
    }
}
