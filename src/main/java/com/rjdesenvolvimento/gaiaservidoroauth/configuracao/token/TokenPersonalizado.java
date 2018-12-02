package com.rjdesenvolvimento.gaiaservidoroauth.configuracao.token;

import com.rjdesenvolvimento.gaiaservidoroauth.domain.usuario.Usuario;
import com.rjdesenvolvimento.gaiaservidoroauth.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TokenPersonalizado extends JwtAccessTokenConverter {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Usuario usuario = usuarioRepository.findByLogin(authentication.getName());
        Map<String, Object> informacaoAdicional = new HashMap<>() {{
            put("idFuncionario", usuario.getIdFuncionario());
            put("idEmpresa", usuario.getIdEmpresa());
            put("perfis", usuario.descricaoPerfil());
            put("login", usuario.getLogin());
        }};
        var defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        defaultOAuth2AccessToken.setAdditionalInformation(informacaoAdicional);
        return super.enhance(defaultOAuth2AccessToken, authentication);
    }
}
