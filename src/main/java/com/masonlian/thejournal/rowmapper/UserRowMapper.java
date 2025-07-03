package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.constant.Level;
import com.masonlian.thejournal.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int i ) throws SQLException {
        User user = new User();
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));

        String levelStr= rs.getString("level");
        Level level = Level.valueOf(levelStr);
        user.setLevel(level);

        user.setUserId(rs.getInt("user_id"));
        user.setCreatedTime(rs.getTimestamp("created_time"));
        user.setLastModifiedTime(rs.getTimestamp("last_modified_time"));

        return user;

    }

}
