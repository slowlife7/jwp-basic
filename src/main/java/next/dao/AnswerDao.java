package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Answer;
import next.model.Question;

import java.util.List;

public class AnswerDao {

    public void insert(Answer answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO ANSWERS(writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, answer.getWriter(), answer.getContents(), answer.getCreatedDate(),
                answer.getQuestionId());
    }

    public Answer findByAnswerId(final long answerId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE answerId=?";

        RowMapper<Answer> rm = rs -> new Answer(rs.getLong("answerId"), rs.getString("writer"),
                rs.getString("contents"), rs.getDate("createdDate"), rs.getLong("questionId"));

        return jdbcTemplate.queryForObject(sql, rm, answerId);
    }

    public long countByQuestionId(final long questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT answerId FROM ANSWERS WHERE questionId=?";

        RowMapper<Integer> rm = rs -> rs.getInt("answerId");

        return jdbcTemplate.query(sql, rm, questionId).size();
    }

    public List<Answer> findByQuestionId(long questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE questionId=?";

        RowMapper<Answer> rm = rs -> new Answer(rs.getLong("answerId"), rs.getString("writer"),
                rs.getString("contents"), rs.getDate("createdDate"), rs.getLong("questionId"));

        return jdbcTemplate.query(sql, rm, questionId);
    }
}
