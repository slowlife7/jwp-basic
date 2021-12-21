package next.controller;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class QnaAnswerController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if(!UserSessionUtils.isLogined(req.getSession())) {
            return "redirect:/users/loginForm";
        }

        User user = UserSessionUtils.getUserFromSession(req.getSession());
        AnswerDao answerDao = new AnswerDao();
        answerDao.insert(new Answer(0L, user.getUserId(), req.getParameter("contents"),
                new Date(), Long.parseLong(req.getParameter("questionId"))));

        QuestionDao questionDao = new QuestionDao();
        questionDao.updateCountOfAnswer(Long.parseLong(req.getParameter("questionId")));

        return "/qna/show";
    }
}
