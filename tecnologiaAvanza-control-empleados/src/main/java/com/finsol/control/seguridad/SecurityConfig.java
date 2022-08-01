package com.finsol.control.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
//        return super.userDetailsService();
        UserDetails usuario = User.withUsername("gaspar")
                .password("$2a$10$SY05n2L4Ap5ev3BKDDR0A.6FxjKmf6.QWhj.vfdrU.zJvcrIOtB1a")
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("$2a$10$SY05n2L4Ap5ev3BKDDR0A.6FxjKmf6.QWhj.vfdrU.zJvcrIOtB1a")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(usuario,admin);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/eliminar/*","/form/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll();

    }
}
