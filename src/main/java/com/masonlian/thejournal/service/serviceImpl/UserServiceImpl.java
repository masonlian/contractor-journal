package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.dao.UserDao;
import com.masonlian.thejournal.dto.request.UserLogInRequest;
import com.masonlian.thejournal.dto.request.UserRegisterRequest;
import com.masonlian.thejournal.model.User;
import com.masonlian.thejournal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId){

        return userDao.getUserById(userId);

    }

    @Override
    public User logIn(UserLogInRequest userLogInRequest) {

        User user = userDao.getUserByEmail(userLogInRequest.getEmail());
                if (user.getEmail()==null)
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                if (user.getPassword().equals(userLogInRequest.getPassword()))
                   return user;
                else
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

}
