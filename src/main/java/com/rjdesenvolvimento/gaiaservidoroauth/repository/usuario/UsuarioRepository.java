package com.rjdesenvolvimento.gaiaservidoroauth.repository.usuario;

import com.rjdesenvolvimento.gaiaservidoroauth.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Transactional(readOnly = true)
    Usuario findByLogin(String login);
}
