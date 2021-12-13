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
        JdbcTemplate insertJdbcTemplate = new JdbcTemplate() {

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException{
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };

        insertJdbcTemplate.update("INSERT INTO USERS VALUES (?, ?, ?, ?)");
    }

    public void update(User user) throws SQLException {
            JdbcTemplate updateJdbcTemplate = new JdbcTemplate() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException{
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
            }
        };
        updateJdbcTemplate.update("UPDATE USERS SET password=?, name=?, email=? WHERE userid=?");
    }

    public List<User> findAll() throws SQLException {
        SelectJdbcTemplate selectJdbcTemplate = new SelectJdbcTemplate() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return new User(resultSet.getString("userId"), resultSet.getString("password"), resultSet.getString("name"),
                            resultSet.getString("email"));
                }
                return null;
            }
        };
        return selectJdbcTemplate.query("SELECT userId, password, name, email FROM USERS");
    }

    public User findByUserId(String userId) throws SQLException {
        SelectJdbcTemplate selectJdbcTemplate = new SelectJdbcTemplate() {
            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException{
                if (resultSet.next()) {
                    return new User(resultSet.getString("userId"), resultSet.getString("password"), resultSet.getString("name"),
                            resultSet.getString("email"));
                }
                return null;
            }

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException{
                pstmt.setString(1, userId);
            }
        };
        return (User)selectJdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?");
    }
}
