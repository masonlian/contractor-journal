package com.masonlian.thejournal.config;


import com.masonlian.thejournal.model.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.Map;
import java.util.function.Function;

//負責生成Token的Class ，

@Component
public class JwtTokenUtil {

    final  static Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Autowired
    private JwtProperties jwtProperties;


    public String getUsernameFromToken(String token) {

        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver ){

        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);

    }

    public Claims extractAllClaims(String token){

        try { Claims claims = Jwts.parserBuilder() //建造者模式
                .setSigningKey(jwtProperties.getSecret()) //設定參數
                .build()  //封裝成完整的姐
                .parseClaimsJws(token) //資料簽章
                .getBody();//取得Claim
            return claims;}
        catch( ExpiredJwtException e){
            log.warn("JWT過期");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token 已經過期");
                   }
            catch(JwtException e) {
                log.warn("JWT無效");}
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token <UNK>");

    }



    public String generateToken(Map<String,Object> claims, User user  ) {


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                .signWith(SignatureAlgorithm.HS512,jwtProperties.getSecret())
                .compact();

    }


}
