package com.rjdesenvolvimento.gaiaservidoroauth.service.instanciar;

import com.rjdesenvolvimento.gaiaservidoroauth.configuracao.encriptar.Encriptar;
import com.rjdesenvolvimento.gaiaservidoroauth.domain.token.OAuthClientDetails;
import com.rjdesenvolvimento.gaiaservidoroauth.repository.token.OAuthClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstanciarOAuthClientDetailsService {

    private final OAuthClientDetailsRepository oAuthClientDetailsRepository;
    private final Encriptar encriptar;

    @Autowired
    public InstanciarOAuthClientDetailsService(OAuthClientDetailsRepository oAuthClientDetailsRepository, Encriptar encriptar) {
        this.oAuthClientDetailsRepository = oAuthClientDetailsRepository;
        this.encriptar = encriptar;
    }

    public void instanciarGaiaPessoas() {
        var oAuthClientDetails = new OAuthClientDetails("gaia_pessoas", encriptar.passwordEncoder().encode("1234"),
                "usuario_resource", "ROLE_RJDESENVOLVIMENTO", "authorization_code,password,refresh_token,implicit",
                null, null, 9000, 9000, "{}", null);
        oAuthClientDetailsRepository.save(oAuthClientDetails);
    }
}
