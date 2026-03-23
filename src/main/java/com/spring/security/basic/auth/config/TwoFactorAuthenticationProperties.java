package com.spring.security.basic.auth.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("feature.2fa")
@Component
@Data
public class TwoFactorAuthenticationProperties {

    private Boolean enabled;
    private String provider;
}
