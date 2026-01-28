package com.savio.supermercado.model;

import com.savio.supermercado.service.GeradorCodigo;

import java.util.HashSet;
import java.util.Set;

public class Funcionario extends Pessoa implements GeradorCodigo {
    private Integer matricula;
    static Set<Integer> matriculasGeradas = new HashSet<>();

    public Funcionario(String nome, String cpf) {
        super(nome, cpf);
        this.matricula = gerarCodigo();
    }

    public Integer getMatricula() {
        return matricula;
    }

    @Override
    public int gerarCodigo() {
        int codigo;

        do {
            codigo = (int) (Math.random() * 99) + 1;
        } while (matriculasGeradas.contains(codigo));

        matriculasGeradas.add(codigo);
        return codigo;
    }
}
