package com.masonlian.thejournal.dto;

import com.masonlian.thejournal.constant.Level;
import com.masonlian.thejournal.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//分配權限
public class CustomUserDetails implements UserDetails {
    private String email;
    private String password;
    private Level level;


    public CustomUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.level = user.getLevel();
    }

    @Override
    public Collection<?extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(level.toString()));
    }
    @Override
    public String getPassword() {return password;}
    @Override
    public String getUsername() {return email;}

    public Level getLevel() {return level;}




    @Override
    public boolean isAccountNonExpired() {return true;}
    @Override
    public boolean isAccountNonLocked() {return true;}
    @Override
    public boolean isCredentialsNonExpired() {return true;}
    @Override
    public boolean isEnabled() {return true;}

}
