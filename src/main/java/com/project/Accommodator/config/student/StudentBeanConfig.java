package com.project.Accommodator.config.student;//package com.project.Accommodator.config.student;
//
//import com.project.Accommodator.config.owner.OwnerAuthenticationManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//
//@Configuration
//public class StudentBeanConfig {
//
//    @Bean(name = "studentLogoutHandler")
//    public LogoutHandler StudentLogoutHandler() {
//        return new StudentLogoutHandler();
//
//    }
//
//    @Bean(name = "studentAuthenticationManager")
//    public AuthenticationManager studentAuthenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//}
