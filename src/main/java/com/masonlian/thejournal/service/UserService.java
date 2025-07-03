package com.masonlian.thejournal.service;

import com.masonlian.thejournal.dto.request.UserLogInRequest;
import com.masonlian.thejournal.dto.request.UserRegisterRequest;
import com.masonlian.thejournal.model.User;

public interface UserService
{
    Integer createUser(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
    User logIn( UserLogInRequest userLogInRequest);




}
