package next.controller;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class QnaQuestionController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(new Question(0L, req.getParameter("writer"),
                req.getParameter("title"),
                req.getParameter("contents"), new Date(), 0));
        return "redirect:/";
    }
}
