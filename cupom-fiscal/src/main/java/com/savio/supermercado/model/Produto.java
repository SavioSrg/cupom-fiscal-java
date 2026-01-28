package com.savio.supermercado.model;

import com.savio.supermercado.service.GeradorCodigo;

import java.util.HashSet;
import java.util.Set;

public class Produto implements GeradorCodigo {
    String nome;
    double preco;
    int codigo;
    static Set<Integer> codigosGerados = new HashSet<>();

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
        this.codigo = gerarCodigo();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public int gerarCodigo() {
        int codigo;

        do {
            codigo = (int) (Math.random() * 9999) + 1;
        } while (codigosGerados.contains(codigo));

        codigosGerados.add(codigo);
        return codigo;
    }

    @Override
    public String toString() {
        return  nome +
                ", preco=" + preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return codigo == produto.codigo;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(codigo);
    }
}
