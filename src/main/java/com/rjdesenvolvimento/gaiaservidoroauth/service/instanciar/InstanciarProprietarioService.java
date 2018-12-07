package com.rjdesenvolvimento.gaiaservidoroauth.service.instanciar;

import com.rjdesenvolvimento.gaiaservidoroauth.configuracao.encriptar.Encriptar;
import com.rjdesenvolvimento.gaiaservidoroauth.domain.usuario.Usuario;
import com.rjdesenvolvimento.gaiaservidoroauth.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstanciarProprietarioService {

    private final UsuarioRepository usuarioRepository;
    private final Encriptar encriptar;

    @Autowired
    public InstanciarProprietarioService(UsuarioRepository usuarioRepository, Encriptar encriptar) {
        this.usuarioRepository = usuarioRepository;
        this.encriptar = encriptar;
    }

    public void instanciarUsuarioRJDesenvolvimento() {
        var usuarioRJDesenvolvimento = new Usuario(true, 1L, 1L, "rjdesenvolvimento@outlook.com",
                encriptar.passwordEncoder().encode("segredo"));
        usuarioRJDesenvolvimento.adicionarPerfil(777);
        usuarioRepository.save(usuarioRJDesenvolvimento);
    }

}
