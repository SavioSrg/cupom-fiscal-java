package com.savio.supermercado.model;

import java.util.HashMap;
import java.util.Map;

public class Carrinho {
    private Map<Produto, Integer> produtosCarrinho = new HashMap<>();

    public Map<Produto, Integer> getProdutosCarrinho() {
        return produtosCarrinho;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        if (quantidade <= 0) return;

        int qtdAtual = produtosCarrinho.getOrDefault(produto, 0);
        produtosCarrinho.put(produto, qtdAtual + quantidade);
    }

    public void removerItem(Produto produto, int quantidade) {
        if (!produtosCarrinho.containsKey(produto)) return;

        int qtdAtual = produtosCarrinho.get(produto);

        if (quantidade >= qtdAtual) {
            produtosCarrinho.remove(produto);
        } else {
            produtosCarrinho.put(produto, qtdAtual - quantidade);
        }
    }

    public double calcularSubTotal() {
        double subtotal = 0.0;

        for (Map.Entry<Produto, Integer> entry : produtosCarrinho.entrySet()) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();

            subtotal += produto.getPreco() * quantidade;
        }
        return subtotal;
    }
}
