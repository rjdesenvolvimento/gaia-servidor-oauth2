package com.rjdesenvolvimento.gaiaservidoroauth.configuracao.instanciar;

import com.rjdesenvolvimento.gaiaservidoroauth.service.instanciar.InstanciarOAuthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstanciarToken {

    private final InstanciarOAuthClientDetailsService instanciarOAuthClientDetailsService;

    @Autowired
    public InstanciarToken(InstanciarOAuthClientDetailsService instanciarOAuthClientDetailsService) {
        this.instanciarOAuthClientDetailsService = instanciarOAuthClientDetailsService;
    }

    @Bean
    public boolean instanciarTokens() {
        instanciarOAuthClientDetailsService.instanciarGaiaPessoas();
        return true;
    }
}
