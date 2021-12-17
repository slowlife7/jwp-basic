package next.dao;

import core.exception.DataAccessException;
import core.jdbc.ConnectionManager;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public void update(String query, PreparedStatementSetter preparedStatementSetter) {

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt=con.prepareStatement(query)){

            preparedStatementSetter.setValues(pstmt);
            pstmt.executeUpdate();

        } catch(SQLException ex){
            throw new DataAccessException(ex);
        }
    }

    public void update(String query, Object... values) {

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt=con.prepareStatement(query)){
            int i = 1;
            for(Object obj : values) {
                pstmt.setObject(i++, obj);
            }
            pstmt.executeUpdate();

        } catch(SQLException ex){
            throw new DataAccessException(ex);
        }
    }

    public <T> List<T> query(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper<T> mapper) throws SQLException{
        List<T> result = new ArrayList<>();
        ResultSet rs = null;

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt=con.prepareStatement(sql)){
            preparedStatementSetter.setValues(pstmt);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                result.add(mapper.mapRow(rs));
            }
            return result;

        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        } finally {
            if(rs!= null) {
                rs.close();
            }
        }
    }

    public <T> List<T> query(String sql, RowMapper<T> mapper, Object... values) throws SQLException{
        List<T> result = new ArrayList<>();
        ResultSet rs = null;

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt=con.prepareStatement(sql)){
            int i = 1;
            for(Object obj : values) {
                pstmt.setObject(i++, obj);
            }
            rs = pstmt.executeQuery();
            while(rs.next()) {
                result.add(mapper.mapRow(rs));
            }
            return result;

        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        } finally {
            if(rs!= null) {
                rs.close();
            }
        }
    }

    public <T> T queryForObject(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper<T> mapper) throws SQLException {
        List<T> result = query(sql, preparedStatementSetter, mapper);
        if(result.isEmpty()) {
            System.out.println("result is empty.");
            return null;
        }
        return result.get(0);
    }

    public <T> T queryForObject(String sql, RowMapper<T> mapper, Object... values) throws SQLException {
        List<T> result = query(sql, mapper, values);
        if(result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }
}
