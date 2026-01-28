package com.savio.supermercado.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class Pedido {
    private Funcionario operador;
    private Cliente cliente;
    private Carrinho itens;
    private LocalDateTime dataHora;
    private FormaDePagamento formaDePagamento;

    public Pedido(Funcionario operador, Cliente cliente, Carrinho itens, FormaDePagamento formaDePagamento) {
        this.operador = operador;
        this.cliente = cliente;
        this.itens = itens;
        this.dataHora = LocalDateTime.now();
        this.formaDePagamento = formaDePagamento;
    }

    private static String centralizar(String texto, int largura) {
        int espacos = (largura - texto.length()) / 2;
        return " ".repeat(Math.max(0, espacos)) + texto;
    }

    public double getSubtotal() {
        return itens.calcularSubTotal();
    }

    public double getDesconto() {
        double subtotal = getSubtotal();

        return switch (formaDePagamento) {
            case PIX -> subtotal * 0.10;
            case DINHEIRO -> subtotal * 0.05;
            case CARTAO -> 0.0;
        };
    }

    public double getTotal() {
        return getSubtotal() - getDesconto();
    }

    public String gerarCupom() {
        StringBuilder sb = new StringBuilder();
        String linha = "-".repeat(50);

        sb.append("\n").append("\n").append("\n");
        sb.append(linha).append("\n");
        sb.append(centralizar("MERCADO GERAL", 50)).append("\n");
        sb.append("DATA: ").append(dataHora.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))).append("\n");
        sb.append("CLIENTE: ").append(cliente.getNome()).append("\n");
        sb.append(linha).append("\n");

        sb.append(String.format("%-25s %-5s %10s\n", "ITEM", "QTD", "VALOR"));
        sb.append(linha).append("\n");

        for (Map.Entry<Produto, Integer> entry : itens.getProdutosCarrinho().entrySet()) {
            Produto p = entry.getKey();
            int qtd = entry.getValue();

            sb.append(String.format(
                    "%-25s %-5d %10.2f\n",
                    p.getNome(),
                    qtd,
                    p.getPreco() * qtd
            ));
        }

        sb.append(linha).append("\n");
        sb.append(String.format("SUBTOTAL: %37.2f\n", getSubtotal()));
        sb.append(String.format("DESCONTO (%s): %31.2f\n", formaDePagamento, getDesconto()));
        sb.append(String.format("TOTAL: %40.2f\n", getTotal()));
        sb.append(linha);

        return sb.toString();
    }


}
