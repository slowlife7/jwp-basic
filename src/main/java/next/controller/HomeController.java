package next.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.db.DataBase;
import next.support.context.ContextLoaderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("users", DataBase.findAll());
        return "/index.jsp";
    }
}
