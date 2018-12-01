package com.rjdesenvolvimento.gaiaservidoroauth.configuracao.seguranca;

import com.rjdesenvolvimento.gaiaservidoroauth.configuracao.encriptar.Encriptar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SegurancaWeb extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final Encriptar encriptar;

    @Autowired
    public SegurancaWeb(@Qualifier("usuarioTokenService") UserDetailsService userDetailsService, Encriptar encriptar) {
        this.userDetailsService = userDetailsService;
        this.encriptar = encriptar;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encriptar.passwordEncoder());
    }
}
