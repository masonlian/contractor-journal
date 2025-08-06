package com.masonlian.thejournal.controller;

import com.masonlian.thejournal.constant.Level;
import com.masonlian.thejournal.dto.LoginResponse;
import com.masonlian.thejournal.dto.request.UserLogInRequest;
import com.masonlian.thejournal.dto.request.UserRegisterRequest;
import com.masonlian.thejournal.model.User;
import com.masonlian.thejournal.service.UserService;
import com.masonlian.thejournal.config.JwtTokenUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private JwtTokenUtil jwtTokenUtil;


  //帳號註冊功能
  @PostMapping("/users/register")
  public ResponseEntity<User> register(@RequestBody UserRegisterRequest userRegisterRequest) {

    Integer userId = userService.createUser(userRegisterRequest);
    User user =  userService.getUserById(userId);

    return ResponseEntity.status(HttpStatus.CREATED).body(user);

  }

  //帳號登入功能
  @PostMapping("/users/login") //這邊要能夠回傳token
  public ResponseEntity<?> logIn(@RequestBody  @Valid UserLogInRequest userLogInRequest){

     Timestamp now = new Timestamp(System.currentTimeMillis());
     userLogInRequest.setLastLoginTime(now);
     User user =  userService.logIn(userLogInRequest);

     System.out.println("使用者姓名為:"+user.getName());

     Map<String,Object> clams = new HashMap<>();


     clams.put("userId",user.getUserId());
     clams.put("userName",user.getName());
     clams.put("email",user.getEmail());
     clams.put("level",user.getLevel().toString());

     jwtTokenUtil.generateToken(clams,user);

     LoginResponse loginResponse = new LoginResponse();
     loginResponse.setToken(jwtTokenUtil.generateToken(clams,user));
     loginResponse.setUserName(user.getName());
     loginResponse.setUserId(user.getUserId());

     return ResponseEntity.status(HttpStatus.OK).body(loginResponse);

  }



}
