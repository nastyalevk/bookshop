package com.nastya.bookShop.config;

import com.nastya.bookShop.security.jwt.AuthTokenFilter;
import com.nastya.bookShop.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final JwtUtils jwtUtils;

    public WebSecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                             JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(jwtUtils, userDetailsService);
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(UrlConst.AuthUrl+"user/new").hasRole("ADMIN")
                .antMatchers(UrlConst.AuthUrl + "**").permitAll()
                .antMatchers("/user/**").hasRole("ADMIN")
                .antMatchers("/review/approve/**").hasRole("ADMIN")
                .antMatchers("/review/delete/**").hasAnyRole("ADMIN", "CLIENT")
                .antMatchers("/review/one/**").hasAnyRole("ADMIN","CLIENT")
                .antMatchers("/book/create").hasRole("OWNER")
                .antMatchers("/stat/proceed/**").hasRole("OWNER")
                .antMatchers("/stat/shop/order/{shopId}").hasRole("OWNER")
                .antMatchers("/book/update").hasRole("OWNER")
                .antMatchers("/shop/create").hasRole("OWNER")
                .antMatchers("/shop/username").hasRole("OWNER")
                .antMatchers("/order/shop/{id}").hasRole("OWNER")
                .antMatchers("/assortment/create").hasRole("OWNER")
                .antMatchers("/assortment/delete/{bookId}/{shopId}").hasRole("OWNER")
                .antMatchers("/review/book/create").hasAnyRole("ADMIN","CLIENT")
                .antMatchers("/review/shop/create").hasAnyRole("ADMIN","CLIENT")
                .antMatchers("/assortment/{bookId}").permitAll()
                .antMatchers("/assortment/price/**").permitAll()
                .antMatchers("/review/book/{id}").permitAll()
                .antMatchers("/review/shop/{id}").permitAll()
                .antMatchers("/book/**").permitAll()
                .antMatchers("/order/**").permitAll()
                .antMatchers("/shop/**").permitAll()
                .antMatchers("/stat/shops").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
