package com.rjdesenvolvimento.gaiaservidoroauth.domain.dto.usuario;

import com.rjdesenvolvimento.gaiaservidoroauth.domain.enums.PerfilEnum;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UsuarioDetail implements UserDetails {

    private Long id;
    private Boolean ativo;
    private Long idEmpresa;
    private Long idFuncionario;
    private String login;
    private String senha;
    private Collection<? extends GrantedAuthority> autorizacoes;

    public UsuarioDetail(Long id, Boolean ativo, Long idEmpresa, Long idFuncionario, String login,
                                            String senha, List<PerfilEnum> perfis) {
        this.id = id;
        this.ativo = ativo;
        this.idEmpresa = idEmpresa;
        this.idFuncionario = idFuncionario;
        this.login = login;
        this.senha = senha;
        this.autorizacoes = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.autorizacoes;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.ativo;
    }
}
