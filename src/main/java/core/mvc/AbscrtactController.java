package core.mvc;

public abstract class AbscrtactController implements Controller {
    protected ModalAndView jspView(String url) {
        return new ModalAndView(new JspView(url));
    }

    protected ModalAndView jsonView(){
        return new ModalAndView(new JsonView());
    }
}
