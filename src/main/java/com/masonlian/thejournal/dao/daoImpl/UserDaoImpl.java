package com.masonlian.thejournal.dao.daoImpl;

import com.masonlian.thejournal.dao.UserDao;
import com.masonlian.thejournal.dto.request.UserLogInRequest;
import com.masonlian.thejournal.dto.request.UserRegisterRequest;
import com.masonlian.thejournal.model.User;
import com.masonlian.thejournal.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql = " INSERT INTO users (email, password, level, created_time, last_modified_time ) VALUE (:email, :password, :level, :created_time, :last_modified_time ) ";
        Map<String, Object> map = new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());
        map.put("level",userRegisterRequest.getLevel());
        Date now = new Date();
        map.put("created_time", now);
        map.put("last_modified_time", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        Integer userId = keyHolder.getKey().intValue();
        return userId;

    }

    public User getUserById(Integer userId) {
        String sql = " SELECT * FROM users WHERE user_id = :user_id";
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        List<User> userList = namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());
        if(userList.size()>0)
            return userList.get(0);
        return null;

    }
    @Override
    public User getUserByEmail(String email) {
        String sql = " SELECT * FROM users WHERE email = :email";
        Map<String, Object> map = new HashMap<>();

        map.put("email", email);
        List<User> userList = namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());
        if(userList.size()>0)
            return userList.get(0);
        return null;
    }

    @Override
    public void lastLoginTime(UserLogInRequest userLogInRequest) {

        String sql= "UPDATE users SET last_login_time = :last_login_time WHERE email = :email ";
        Map<String, Object> map = new HashMap<>();
        map.put("last_login_time",userLogInRequest.getLastLoginTime());
        map.put("email",userLogInRequest.getEmail());
        namedParameterJdbcTemplate.update(sql,map);
    }



}
