package pe.edu.cibertec.farmacia.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import pe.edu.cibertec.farmacia.services.ReportService;

@Configuration
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); 
    }

    @Bean
    public UserDetailsService userDetailsService() {
        User arthur = new User("arthur", passwordEncoder().encode("123456"), Arrays.asList(new SimpleGrantedAuthority("USER")));
        return new InMemoryUserDetailsManager(arthur);
    }


    // @Bean
    // public ReportService reportService(DataSource dataSource) {
    //     return new ReportService(dataSource);
    // }
}
