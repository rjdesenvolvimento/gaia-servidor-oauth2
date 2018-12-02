package com.rjdesenvolvimento.gaiaservidoroauth.configuracao.servidor;

import com.rjdesenvolvimento.gaiaservidoroauth.configuracao.encriptar.Encriptar;
import com.rjdesenvolvimento.gaiaservidoroauth.configuracao.request.Oauth2RequestFactoryPersonalizada;
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

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class ServidorDeAutorizacao extends AuthorizationServerConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final DataSource dataSource;
    private final AuthenticationManager authenticationManager;
    private final ConfiguracaoDeToken configuracaoDeToken;
    private final Oauth2RequestFactoryPersonalizada oauth2RequestFactoryPersonalizada;
    private final Encriptar encriptar;

    @Autowired
    public ServidorDeAutorizacao(@Qualifier("usuarioTokenService") UserDetailsService userDetailsService, DataSource dataSource,
                                 AuthenticationManager authenticationManager, ConfiguracaoDeToken configuracaoDeToken,
                                 Oauth2RequestFactoryPersonalizada oauth2RequestFactoryPersonalizada, Encriptar encriptar) {
        this.userDetailsService = userDetailsService;
        this.dataSource = dataSource;
        this.authenticationManager = authenticationManager;
        this.oauth2RequestFactoryPersonalizada = oauth2RequestFactoryPersonalizada;
        this.encriptar = encriptar;
        this.configuracaoDeToken = configuracaoDeToken;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        System.out.println("AuthorizationServerEndpointsConfigurer");
        endpoints.tokenStore(configuracaoDeToken.tokenStore()).authenticationManager(authenticationManager)
                .tokenEnhancer(configuracaoDeToken.jwtAccessTokenConverter()).userDetailsService(userDetailsService)
                .requestFactory(oauth2RequestFactoryPersonalizada.requestFactory());

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(encriptar.passwordEncoder());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
    }
}
