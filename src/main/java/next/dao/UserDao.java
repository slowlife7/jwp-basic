package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {
        PreparedStatementSetter setter = pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        };
        JdbcTemplate insertJdbcTemplate = new JdbcTemplate();
        insertJdbcTemplate.update("INSERT INTO USERS VALUES (?, ?, ?, ?)", setter);
    }

    public void update(User user) throws SQLException {
        PreparedStatementSetter setter = pstmt -> {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
        };
        JdbcTemplate updateJdbcTemplate = new JdbcTemplate();
        updateJdbcTemplate.update("UPDATE USERS SET password=?, name=?, email=? WHERE userid=?", setter);
    }

    public List<User> findAll() throws SQLException {
        RowMapper mapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return new User(resultSet.getString("userId"), resultSet.getString("password"), resultSet.getString("name"),
                            resultSet.getString("email"));
                }
                return null;
            }
        };

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        return jdbcTemplate.query("SELECT userId, password, name, email FROM USERS", mapper);
    }

    public User findByUserId(String userId) throws SQLException {
        PreparedStatementSetter setter = pstmt -> {
            pstmt.setString(1, userId);
        };
        RowMapper mapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return new User(resultSet.getString("userId"), resultSet.getString("password"), resultSet.getString("name"),
                            resultSet.getString("email"));
                }
                return null;
            }
        };
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        return (User)jdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?",setter, mapper);
    }
}
