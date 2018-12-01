package com.rjdesenvolvimento.gaiaservidoroauth.service.usuario;

import com.rjdesenvolvimento.gaiaservidoroauth.domain.dto.usuario.UsuarioDetail;
import com.rjdesenvolvimento.gaiaservidoroauth.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioTokenService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioTokenService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByLogin(login);
        if (usuario.equals(null)) {
            throw new UsernameNotFoundException("Usuário: " + login + ", não encontrado.");
        }
        var usuarioDetail = new UsuarioDetail(usuario.getId(), usuario.getAtivo(), usuario.getIdEmpresa(), usuario.getIdFuncionario(),
                usuario.getLogin(), usuario.getSenha(), usuario.getPerfis());
        new AccountStatusUserDetailsChecker().check(usuarioDetail);
        return usuarioDetail;
    }
}
