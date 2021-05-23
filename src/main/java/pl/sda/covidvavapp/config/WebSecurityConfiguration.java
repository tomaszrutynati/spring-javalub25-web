package pl.sda.covidvavapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("userPass")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("test")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/user/registration", "/").permitAll()
                .and().authorizeRequests().antMatchers("/facility", "/facility/**").hasRole("ADMIN")
                .and().authorizeRequests().antMatchers("/patient", "/patient/**").authenticated()
                .and().authorizeRequests().antMatchers("/vaccination", "/vaccination/**").authenticated()
                .and().formLogin();
    }
}
