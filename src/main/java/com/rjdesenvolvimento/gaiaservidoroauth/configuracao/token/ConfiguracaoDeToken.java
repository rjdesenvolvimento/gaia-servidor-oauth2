package com.rjdesenvolvimento.gaiaservidoroauth.configuracao.token;

import com.rjdesenvolvimento.gaiaservidoroauth.configuracao.request.Oauth2RequestFactoryPersonalizada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;

@Component
public class ConfiguracaoDeToken {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private Oauth2RequestFactoryPersonalizada oAuth2RequestFactory;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }


    @Bean
    public TokenEndpointAuthenticationFilter tokenEndpointAuthenticationFilter() {
        return new TokenEndpointAuthenticationFilter(authenticationManager, oAuth2RequestFactory);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        var converteToken = new TokenPersonalizado();
        converteToken.setKeyPair(new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "RJDDourados!".toCharArray())
                .getKeyPair("jwt"));
        return converteToken;
    }
}
