package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Question;
import next.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionDao {
    public Question findByQuestionId(final Long questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS WHERE questionId=?";

        RowMapper<Question> rm = rs -> new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"),
                rs.getString("contents"), rs.getDate("createdDate"), rs.getInt("countOfAnswer"));

        return jdbcTemplate.queryForObject(sql, rm, questionId);
    }
    public List<Question> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS ORDER BY createdDate DESC";

        RowMapper<Question> rm = rs -> new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"),
                rs.getString("contents"), rs.getDate("createdDate"), rs.getInt("countOfAnswer"));
        return jdbcTemplate.query(sql, rm);
    }

    public void insert(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO QUESTIONS(writer, title, contents, createdDate, countOfAnswer) VALUES ( ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, question.getWriter(), question.getTitle(),
                question.getContents(), question.getCreatedDate(), question.getCountOfAnswer());
    }
}
