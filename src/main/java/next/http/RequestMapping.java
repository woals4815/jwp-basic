package next.http;

import next.controller.Controller;
import next.controller.CreateUserController;
import next.controller.ForwardController;
import next.controller.ListUserController;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static Map<String, Controller> controllers = new HashMap<>();

    static  {
        ForwardController forwardController = new ForwardController();
        controllers.put("/", forwardController);
        controllers.put("/user/list", new ListUserController());
        controllers.put("/users/loginForm", forwardController);
        controllers.put("/users/form", forwardController);
        controllers.put("/users/create", new CreateUserController());
    }

    static public Controller getController(String uri) {
        return controllers.get(uri);
    }
}
