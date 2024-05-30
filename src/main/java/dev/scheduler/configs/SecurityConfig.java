package dev.scheduler.configs;

import dev.scheduler.entities.auth.BaseRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CharacterEncodingFilter;


import javax.sql.DataSource;
@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "select username, password, enabled "
                                + "from users "
                                + "where username=?")
                .authoritiesByUsernameQuery("select username, role from user_roles where username=?");
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        System.setProperty("https.protocols", "TLSv1.2");
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
        http
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/home/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/admin/**").permitAll()/*hasAnyAuthority(BaseRole.SUPER_USER.getRole())*/
                .antMatchers(HttpMethod.GET,"/api/user").permitAll()
                .antMatchers(HttpMethod.POST,"/api/admin/**").permitAll()/*hasAnyAuthority(BaseRole.SUPER_USER.getRole())*/
                .antMatchers(HttpMethod.POST,"/api/logout").permitAll()
                .antMatchers(HttpMethod.POST,"/api/login").permitAll()
                .antMatchers(HttpMethod.DELETE,"/api/base/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/base/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/base/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/api/base/**").permitAll()
                .antMatchers(HttpMethod.GET,"/emails/text/**").permitAll()
                .antMatchers(HttpMethod.POST,"/emails/text/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/emails/text/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/emails/text/**").permitAll()
                .antMatchers(HttpMethod.GET,"/task/**").permitAll()
                .antMatchers(HttpMethod.POST,"/task/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/task/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/task/**").permitAll()
                .antMatchers(HttpMethod.GET,"/tasks/**").permitAll()
                .antMatchers(HttpMethod.POST,"/tasks/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/tasks/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/tasks/**").permitAll()
                .antMatchers(HttpMethod.GET,"/untask/**").permitAll()
                .antMatchers(HttpMethod.POST,"/untask/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/untask/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/untask/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .and()
                .csrf().disable();
        http.cors().disable();
    }
}