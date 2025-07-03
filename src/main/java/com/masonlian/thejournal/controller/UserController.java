package com.masonlian.thejournal.controller;

import com.masonlian.thejournal.dto.request.UserLogInRequest;
import com.masonlian.thejournal.dto.request.UserRegisterRequest;
import com.masonlian.thejournal.model.User;
import com.masonlian.thejournal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("register")
  public ResponseEntity<User> register(@RequestBody UserRegisterRequest userRegisterRequest) {

    Integer userId = userService.createUser(userRegisterRequest);
    User user =  userService.getUserById(userId);

    return ResponseEntity.status(HttpStatus.CREATED).body(user);

  }

  @PostMapping("login")
  public ResponseEntity<User> logIn( @RequestBody  @Valid UserLogInRequest userLogInRequest){
     User user=  userService.logIn(userLogInRequest);
     return ResponseEntity.status(HttpStatus.OK).body(user);

  }



}
