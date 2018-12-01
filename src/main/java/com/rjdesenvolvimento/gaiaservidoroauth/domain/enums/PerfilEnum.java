package com.rjdesenvolvimento.gaiaservidoroauth.domain.enums;

import lombok.Getter;

@Getter
public enum PerfilEnum {

    NAOSELECIONADO(0, "Não selecionado"),
    ADMINISTRADOR(1, "ROLE_ADMINISTRADOR"),
    VENDEDOR(2, "ROLE_VENDEDOR"),
    CAIXA(3, "ROLE_CAIXA"),
    GERENTE(4, "ROLE_GERENTE"),
    CONTADOR(5, "ROLE_CONTADOR"),
    ALMOXARIFADO(6, "ROLE_ALMOXARIFADO"),
    FORNECEDOR(7, "ROLE_FORNECEDOR"),
    RJDESENVOLVIMENTO(777, "ROLE_RJDESENVOLVIMENTO");

    private int codigo;
    private String descricao;

    PerfilEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static PerfilEnum converteParaEnum(Integer codigo) {
        if (codigo == null) return null;
        for (PerfilEnum paraCada : PerfilEnum.values()) {
            if (codigo.equals(paraCada.getCodigo())) {
                return paraCada;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + codigo);
    }

    public static String descricao(Integer codigo) {
        if (codigo == null) return null;
        for (PerfilEnum paraCada : PerfilEnum.values()) {
            if (codigo.equals(paraCada.getCodigo())) {
                return paraCada.getDescricao();
            }
        }
        throw new IllegalArgumentException("Id inválido: " + codigo);
    }

    public static Integer codigo(Integer codigo) {
        if (codigo == null) return null;
        for (PerfilEnum paraCada : PerfilEnum.values()) {
            if (codigo == paraCada.codigo) {
                return paraCada.getCodigo();
            }
        }
        throw new IllegalArgumentException("Id inválido: " + codigo);
    }
}