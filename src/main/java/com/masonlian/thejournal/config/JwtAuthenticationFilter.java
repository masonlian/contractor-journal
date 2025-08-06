package com.masonlian.thejournal.config;

import com.masonlian.thejournal.constant.Level;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
                                    )
    throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);


            try{  Claims claims = jwtTokenUtil.extractAllClaims(token);//

                  String userName = claims.getSubject();

                  String level = claims.get("level", String.class);
                  String email = claims.get("email", String.class);
                  Integer userId = claims.get("userId", Integer.class);



                  CustomUserDetails customUserDetails =  new CustomUserDetails(email, userId,userName, level);


            List<GrantedAuthority> authorities =
                    List.of(new SimpleGrantedAuthority(level));

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customUserDetails , null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);}
            catch(ExpiredJwtException e){
                System.out.println("Token過期"+e.getMessage());
            }catch(Exception e){
                System.out.println("Token無效"+e.getMessage());
            }
        }

        filterChain.doFilter(request, response);

    }



















}
