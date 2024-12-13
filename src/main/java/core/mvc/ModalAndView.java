package core.mvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModalAndView {
    private View view;
    private Map<String, Object> model = new HashMap<>();
    public ModalAndView(View view) {
        this.view = view;
    }
    public ModalAndView addObject(String attr, Object value) {
        model.put(attr, value);
        return this;
    }
    public Map<String, Object> getModel() {
        return Collections.unmodifiableMap(model);
    }
    public View getView() {
        return view;
    }

}
