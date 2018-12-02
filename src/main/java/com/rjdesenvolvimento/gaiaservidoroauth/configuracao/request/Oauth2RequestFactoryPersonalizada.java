package com.rjdesenvolvimento.gaiaservidoroauth.configuracao.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Oauth2RequestFactoryPersonalizada extends DefaultOAuth2RequestFactory {

    @Autowired
    @Qualifier("tokenStore")
    private TokenStore tokenStore;
    @Autowired
    @Qualifier("usuarioTokenService")
    private UserDetailsService userDetailsService;
    @Autowired
    private ClientDetailsService clientDetailsService;

    public Oauth2RequestFactoryPersonalizada(ClientDetailsService clientDetailsService) {
        super(clientDetailsService);
    }

    @Bean
    public OAuth2RequestFactory requestFactory() {
        var requestFactory = new Oauth2RequestFactoryPersonalizada(clientDetailsService);
        requestFactory.setCheckUserScopes(true);
        return requestFactory;
    }

    @Override
    public TokenRequest createTokenRequest(Map<String, String> requestParameters, ClientDetails authenticatedClient) {
        if (requestParameters.get("grant_type").equals("refresh_token")) {
            var authentication = tokenStore.readAuthenticationForRefreshToken(tokenStore.readRefreshToken(requestParameters
                    .get("refresh_token")));
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    authentication.getName(), null, userDetailsService.loadUserByUsername(authentication.getName())
                    .getAuthorities()));
        }
        return super.createTokenRequest(requestParameters, authenticatedClient);
    }
}
