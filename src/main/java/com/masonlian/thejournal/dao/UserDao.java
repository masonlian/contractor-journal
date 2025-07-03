package com.masonlian.thejournal.dao;

import com.masonlian.thejournal.dto.request.UserRegisterRequest;
import com.masonlian.thejournal.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
    User getUserByEmail(String email);
}
