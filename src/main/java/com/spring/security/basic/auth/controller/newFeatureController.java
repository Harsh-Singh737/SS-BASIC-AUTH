package com.spring.security.basic.auth.controller;


import com.spring.security.basic.auth.config.TwoFactorAuthenticationProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newFeature")
public class newFeatureController {

    @Value("${feature.2fa.enabled}")
    private Boolean is2fa;

    @GetMapping("/2fa")
    public Boolean is2fa(){
        return is2fa;
    }


    @Autowired
    private Environment environment;

    @GetMapping("/2faUsingPropertyInterface")
    public String is2faUsingPropertyInterface(){
        Boolean is2fa = environment.getProperty("feature.2fa.enabled", Boolean.class);
        if (is2fa){
            String provider = environment.getProperty("feature.2fa.provider");
            if (provider.equals("sms")){
                return "Sms service is available for default servers.";
            }else if (provider.equals("email")){
                return "Email service only for dev and prod servers.";
            }
        }
        return "I think you entered in wrong controller. || or you testing using default environment.";
    }


    @Autowired
    private TwoFactorAuthenticationProperties twoFactorAuthenticationProperties;


    @GetMapping("/2faUsing2faPropertyFile")
    public String is2faUsing2faPropertyFile(){
        if (twoFactorAuthenticationProperties.getEnabled()){
            if (twoFactorAuthenticationProperties.getProvider().equals("sms")){
                return "Sms service is available for default servers.";
            } else if (twoFactorAuthenticationProperties.getProvider().equals("email")) {
                return "Email service only for dev and prod servers.";
            }
        }

        return "I think you entered in wrong controller. || or you testing using default environment.";
    }

}
