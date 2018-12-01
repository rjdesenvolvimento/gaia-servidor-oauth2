package com.rjdesenvolvimento.gaiaservidoroauth.configuracao.token;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
public class ConfiguracaoDeToken {


    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        var converteToken = new TokenPersonalizado();
        converteToken.setKeyPair(new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "RJDDourados!".toCharArray())
                .getKeyPair("jwt"));
        return converteToken;
    }
}
