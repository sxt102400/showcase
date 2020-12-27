package com.shawn.config;

import com.shawn.auth.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyUserDetailsService myUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //基于内存来存储用户信息
        //auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
        //        .withUser("user").password(new BCryptPasswordEncoder().encode("123")).roles("USER").and()
        //       .withUser("admin").password(new BCryptPasswordEncoder().encode("456")).roles("USER", "ADMIN");
        //设置用户信息加载工具类（获取账号，密码，权限）
        String pass = passwordEncoder().encode("123456");
        auth
                .userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

 /*   @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(
                        authorize -> authorize
                                //设置访问允许的路径
                                .antMatchers("/hello", "/404", "/error/*").permitAll()
                                //.antMatchers("/user/**").hasRole("USER")
                                //而其他的请求都需要认证
                                .anyRequest().authenticated()
                )
                //修改Spring Security默认的登陆界面
                //.formLogin(Customizer.withDefaults())
                 .formLogin(
                         formLogin -> formLogin.loginPage("/login")
                                 .loginProcessingUrl("/loginAjax").usernameParameter("username").passwordParameter("password")
                                 .successHandler(new MyAuthenticationSuccessHandler())
                                 .failureHandler(new MyAuthenticationFailureHandler()).permitAll()
                 )
                //登出
                .logout(logout -> logout.logoutUrl("/logout").permitAll())
                //过滤器
                //.addFilter(new BeforeLoginFilter())
                .httpBasic()
                .and()
                .csrf().disable();
    }

    static class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.write("{\"code\":0,\"isLogin\":true,\"msg\":\"登录成功\"}");
        }
    }

    static class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("{\"code\":1,\"isLogin\":false,\"msg\":\"登录失败\"}");
        }
    }

}

