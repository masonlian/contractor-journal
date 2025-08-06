package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.dao.UserDao;
import com.masonlian.thejournal.config.CustomUserDetails;
import com.masonlian.thejournal.dto.request.UserLogInRequest;
import com.masonlian.thejournal.dto.request.UserRegisterRequest;
import com.masonlian.thejournal.model.User;
import com.masonlian.thejournal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    final static Logger log =  LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(userRegisterRequest.getPassword());

        UserRegisterRequest hasedUserRegisterRequest = new UserRegisterRequest();
        hasedUserRegisterRequest.setPassword(hashedPassword);
        hasedUserRegisterRequest.setEmail(userRegisterRequest.getEmail());
        hasedUserRegisterRequest.setLevel(userRegisterRequest.getLevel());

        return userDao.createUser(hasedUserRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId){

        return userDao.getUserById(userId);

    }

    @Override
    public User  logIn(UserLogInRequest userLogInRequest) {

        User user = userDao.getUserByEmail(userLogInRequest.getEmail());
        System.out.println("使用者在Service層為:"+user.getUserId());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                if (user.getEmail()==null){
                    log.warn("該電子信箱尚未註冊");
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);}
                if (passwordEncoder.matches(userLogInRequest.getPassword(),user.getPassword())) {

                    userDao.lastLoginTime(userLogInRequest);


                    return user;
                }
                else
                    log.warn("密碼不正確");
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }



}
