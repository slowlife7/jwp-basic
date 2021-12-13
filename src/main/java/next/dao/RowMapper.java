package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {
    public Object mapRow(ResultSet resultSet) throws SQLException;
}
