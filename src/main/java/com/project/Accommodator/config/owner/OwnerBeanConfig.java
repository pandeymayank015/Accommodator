package com.project.Accommodator.config.owner;//package com.project.Accommodator.config.owner;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//
//@Configuration
//public class OwnerBeanConfig {
//
//    @Bean(name = "ownerLogoutHandler")
//    public LogoutHandler ownerLogoutHandler() {
//        return new OwnerLogoutHandler();
//
//    }
//
//    @Bean(name = "ownerAuthenticationManager")
//    public AuthenticationManager ownerAuthenticationManager(AuthenticationConfiguration config) throws Exception {
//        return  config.getAuthenticationManager();
//    }
//}
