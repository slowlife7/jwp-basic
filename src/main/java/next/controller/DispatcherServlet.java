package next.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse)res;
        String requestURI = httpRequest.getRequestURI();

        logger.info("requestURI :{}",requestURI);
        logger.trace("test");
        logger.debug("test2");

        Controller controller = RequestMapping.getController(requestURI);
        try {

            if (controller != null) {
                String result = controller.execute(httpRequest, httpResponse);
                if (result.startsWith("redirect:")) {
                    httpResponse.sendRedirect(result.split("redirect:")[1]);
                } else {
                    RequestDispatcher rd = req.getRequestDispatcher(result);
                    rd.forward(httpRequest, httpResponse);
                }
            }

        } catch (Exception e ) {

        }
    }
}

