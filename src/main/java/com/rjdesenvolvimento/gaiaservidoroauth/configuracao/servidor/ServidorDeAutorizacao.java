package com.rjdesenvolvimento.gaiaservidoroauth.configuracao.servidor;

import com.rjdesenvolvimento.gaiaservidoroauth.configuracao.encriptar.Encriptar;
import com.rjdesenvolvimento.gaiaservidoroauth.configuracao.token.ConfiguracaoDeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class ServidorDeAutorizacao extends AuthorizationServerConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final Encriptar encriptar;
    public final ConfiguracaoDeToken configuracaoDeToken;

    @Autowired
    public ServidorDeAutorizacao(@Qualifier("usuarioTokenService") UserDetailsService userDetailsService, AuthenticationManager authenticationManager,
                                 Encriptar encriptar, ConfiguracaoDeToken configuracaoDeToken) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.encriptar = encriptar;
        this.configuracaoDeToken = configuracaoDeToken;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(configuracaoDeToken.tokenStore()).authenticationManager(authenticationManager)
                .tokenEnhancer(configuracaoDeToken.jwtAccessTokenConverter());

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("cliente").secret(encriptar.passwordEncoder().encode("segredo")).scopes("read,write,trust").authorizedGrantTypes("password");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
}
