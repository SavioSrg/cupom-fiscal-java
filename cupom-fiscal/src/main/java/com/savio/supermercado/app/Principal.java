package com.savio.supermercado.app;

import com.savio.supermercado.model.*;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Cliente e Funcionário
        Cliente cliente = new Cliente("Gustavo Chaves", "800.631.412-34");
        Funcionario operador = new Funcionario("Maria Souza", "123.456.789-00");

        // Produtos
        Produto leite = new Produto("Leite Integral", 4.50);
        Produto pao = new Produto("Pão Francês Kg", 9.90);
        Produto cafe = new Produto("Café Premium", 15.00);
        Produto manteiga = new Produto("Manteiga", 12.50);

        // Carrinho
        Carrinho carrinho = new Carrinho();
        carrinho.adicionarItem(leite, 1);
        carrinho.adicionarItem(pao, 1);
        carrinho.adicionarItem(cafe, 1);
        carrinho.adicionarItem(manteiga, 1);

        // 4️⃣ Forma de pagamento
        System.out.println("\nEscolha a forma de pagamento:");
        System.out.println("1 - PIX (10% desc)");
        System.out.println("2 - DINHEIRO (5% desc)");
        System.out.println("3 - CARTAO (sem desc)");
        System.out.print("Opção: ");

        int opcao = sc.nextInt();

        FormaDePagamento formaPagamento = switch (opcao) {
            case 1 -> FormaDePagamento.PIX;
            case 2 -> FormaDePagamento.DINHEIRO;
            default -> FormaDePagamento.CARTAO;
        };

        // 5️⃣ Pedido
        Pedido pedido = new Pedido(operador, cliente, carrinho, formaPagamento);

        // 6️⃣ Imprimir cupom
        System.out.println(pedido.gerarCupom());

        sc.close();
    }
}
