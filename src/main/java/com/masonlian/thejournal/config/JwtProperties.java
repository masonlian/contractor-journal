package com.masonlian.thejournal.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;


@Configuration
@ConfigurationProperties(prefix = "jwt" )
@Data
public class JwtProperties {

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    private String secret;
    private Long expiration;


}
