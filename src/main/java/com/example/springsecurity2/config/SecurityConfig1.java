package com.example.springsecurity2.config;

import com.example.springsecurity2.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Classname SecurityConfig1
 * @Description TODO
 * @Date 2022/8/20 17:42
 * @Created by uuz
 */

//开启注解控制的权限认证
@EnableWebSecurity  // 添加 security 过滤器
@EnableGlobalMethodSecurity(prePostEnabled = true)	// 启用方法级别的权限认证
@Configuration
public class SecurityConfig1 {

    //密码校验
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 基于 token，不需要 csrf
                .csrf().disable()
                // 基于 token，不需要 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                // 下面开始设置权限
                .authorizeRequests(authorize -> authorize
                        // 请求放开 permitAll->不管登入,不登入 都能访问  anonymous->允许匿名用户访问,不允许已登入用户访问
                        .antMatchers("/user/login").permitAll()

                        //基于配置的权限验证，，可用注解，四种默认提供方法 hasAnyAuthority hasAuthority  hasRole hasAnyRole，后两个会默认在前面加上 'Role_' 前缀，慎用
//                        .antMatchers("/testCors/**，“/*/**").hasAuthority("system:dept:list222")

                        // 其他地址的访问均需验证权限
                        .anyRequest().authenticated()
                )

                // 添加 JWT 过滤器，JWT 过滤器在用户名密码认证过滤器之前 addFilterBefore 添加 过滤器 之前
                //第一个参数为自定需要添加的过滤器  第二个参数为在哪一个过滤器之前，意思就是jwtAuthenticationTokenFilter配置在UsernamePasswordAuthenticationFilter之前
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)

                //设置 jwtAuthError 处理认证失败、鉴权失败
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .and()

                //允许跨域
                .cors()
                .and()

                .build();
    }

}
