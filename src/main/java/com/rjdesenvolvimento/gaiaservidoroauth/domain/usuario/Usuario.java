package com.rjdesenvolvimento.gaiaservidoroauth.domain.usuario;

import com.rjdesenvolvimento.gaiaservidoroauth.domain.EntidadeAbstrata;
import com.rjdesenvolvimento.gaiaservidoroauth.domain.enums.PerfilEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = "usuario", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"idEmpresa", "idFuncionario"}),
        @UniqueConstraint(columnNames = {"idEmpresa", "login"})
})
public class Usuario extends EntidadeAbstrata {

    @Column(nullable = false)
    private Boolean ativo;
    @Column(nullable = false)
    private Long idEmpresa;
    @Column(nullable = false)
    private Long idFuncionario;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String senha;
    @CollectionTable(name = "perfis", schema = "usuario")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> perfis = new ArrayList();

    public Usuario(Boolean ativo, Long idEmpresa, Long idFuncionario, String login, String senha) {
        this.ativo = ativo;
        this.idEmpresa = idEmpresa;
        this.idFuncionario = idFuncionario;
        this.login = login;
        this.senha = senha;
    }

    public List<PerfilEnum> getPerfis() {
        return perfis.stream().map(PerfilEnum::converteParaEnum).collect(Collectors.toList());
    }

    public List<String> descricaoPerfil() {
        return perfis.stream().map(PerfilEnum::descricao).collect(Collectors.toList());
    }
    public void adicionarPerfil(Integer perfis) {
        this.perfis.add(PerfilEnum.codigo(perfis));
    }

    public void removerPerfil(Integer perfis) {
        this.perfis.remove(PerfilEnum.codigo(perfis));
    }
}
