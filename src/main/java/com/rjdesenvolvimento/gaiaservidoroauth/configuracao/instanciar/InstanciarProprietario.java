package com.rjdesenvolvimento.gaiaservidoroauth.configuracao.instanciar;

import com.rjdesenvolvimento.gaiaservidoroauth.service.instanciar.InstanciarProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstanciarProprietario {

    private final InstanciarProprietarioService instanciarProprietarioService;

    @Autowired
    public InstanciarProprietario(InstanciarProprietarioService instanciarProprietarioService) {
        this.instanciarProprietarioService = instanciarProprietarioService;
    }

    @Bean
    public boolean instanciar() {
        instanciarProprietarioService.instanciarUsuarioRJDesenvolvimento();
        return true;
    }
}
