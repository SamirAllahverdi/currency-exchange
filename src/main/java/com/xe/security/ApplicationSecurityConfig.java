package com.xe.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


//    private ApplicationSecurityConfig(TestInitialUser initialUsers) {
//        initialUsers.create();
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        "/scripts/**",   "/fonts/**"
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/img/**", "/main-page", "/register","/history").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                    .loginPage("/index")
                    .loginPage("/login")
                .permitAll()
//                    .loginProcessingUrl("/templates/index.html")
                    .usernameParameter("email")
                    .passwordParameter("password")
//                .defaultSuccessUrl("/main-page-authorized", true)
                .and()
                .logout()
//                    .logoutUrl("/logout");
.permitAll();
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
//                .antMatchers("/api/**").hasRole(STUDENT.name())
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .defaultSuccessUrl("/courses", true)
//                .passwordParameter("password")
//                .usernameParameter("username")
//                .and()
//                .rememberMe()
//                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
//                .key("somethingverysecured")
//                .rememberMeParameter("remember-me")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // https://docs.spring.io/spring-security/site/docs/4.2.12.RELEASE/apidocs/org/springframework/security/config/annotation/web/configurers/LogoutConfigurer.html
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID", "remember-me")
//                .logoutSuccessUrl("/login");
    }


}
