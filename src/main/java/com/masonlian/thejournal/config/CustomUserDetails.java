package com.masonlian.thejournal.config;

import com.masonlian.thejournal.constant.Level;
import com.masonlian.thejournal.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

//分配權限

public class CustomUserDetails implements UserDetails {

    private String email;
    private Integer userId;
    private String level;
    private String userName;


    public CustomUserDetails(String email, Integer userId, String userName, String level) {
        this.email = email;
        this.userId = userId;
        this.userName = userName;
        this.level = level;
    }



    public CustomUserDetails(User user) {
        this.email = user.getEmail();
        this.userId = user.getUserId();
        this.userName = user.getName();
        this.level = user.getLevel().toString();
    }

    @Override
    public Collection<?extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(level.toString()));
    }
    @Override
    public String  getPassword(){return null;};

    @Override
    public String getUsername() {return userName;}

    public String getLevel() {return level;}

    public Integer getUserId() {return userId;}

    public String getEmail() {return email;}




    @Override
    public boolean isAccountNonExpired() {return true;}
    @Override
    public boolean isAccountNonLocked() {return true;}
    @Override
    public boolean isCredentialsNonExpired() {return true;}
    @Override
    public boolean isEnabled() {return true;}

}
