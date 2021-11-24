package next.controller;

import next.support.context.ContextLoaderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller{
    private static final Logger logger = LoggerFactory.getLogger(ForwardController.class);
    private final String viewPath;

    public ForwardController(String viewPath) {
        this.viewPath = viewPath;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return viewPath;
    }
}
