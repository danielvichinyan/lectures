package com.knowit.lectures.security;

import com.knowit.lectures.security.filter.FilterHeader;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final FilterHeader filterHeader;

    public WebSecurityConfiguration(FilterHeader filterHeader) {
        this.filterHeader = filterHeader;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .and()
                .addFilterBefore(this.filterHeader, UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }
}

