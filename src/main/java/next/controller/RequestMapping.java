package next.controller;

import next.controller.Controller;
import next.controller.ListUserController;
import next.controller.LoginController;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static Map<String, Controller> controllers = new HashMap<String, Controller>();

    static {
        controllers.put("/user/list", new ListUserController());
        controllers.put("/", new HomeController());
        controllers.put("/users/create", new CreateUserController());
        controllers.put("/users/login", new LoginController());
        controllers.put("/users/logout", new LogoutController());
        controllers.put("/users/update", new UpdateUserController());
        controllers.put("/users/form", new ForwardController("/user/form.jsp"));
        controllers.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        controllers.put("/users/updateForm", new ForwardController("/user/updateForm.jsp"));
    }

    public static Controller getController(String url) {
        return controllers.get(url);
    }
}
