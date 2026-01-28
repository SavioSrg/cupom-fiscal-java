package com.savio.supermercado.app;

import com.savio.supermercado.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Produto> catalogo = Arrays.asList(
                new Produto("Leite Integral", 4.50),
                new Produto("Pão Francês Kg", 9.90),
                new Produto("Café Premium", 15.00),
                new Produto("Manteiga", 12.50),
                new Produto("Arroz Tipo 1 (5kg)", 25.90),
                new Produto("Feijão Carioca (1kg)", 8.40),
                new Produto("Açúcar Refinado (1kg)", 4.20),
                new Produto("Óleo de Soja", 7.80),
                new Produto("Macarrão Espaguete", 5.30),
                new Produto("Sabonete", 2.50),
                new Produto("Detergente", 3.10)
        );

        Cliente cliente = new Cliente("Visitante", "000.000.000-00");
        Funcionario operador = new Funcionario("Caixa Central", "111.222.333-44");
        Carrinho carrinho = new Carrinho();

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- MERCADO GERAL ---");
            System.out.println("1. Ver Catálogo e Adicionar Item");
            System.out.println("2. Ver Carrinho");
            System.out.println("3. Remover Item");
            System.out.println("4. Finalizar Pedido");
            System.out.println("0. Sair");
            System.out.print("\nEscolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.println("\nCATÁLOGO:");
                    for (int i = 0; i < catalogo.size(); i++) {
                        System.out.printf(
                                "%d. %s - R$ %.2f%n",
                                i,
                                catalogo.get(i).getNome(),
                                catalogo.get(i).getPreco()
                        );
                    }

                    System.out.print("Digite o número do produto: ");
                    int index = sc.nextInt();

                    System.out.print("Quantidade: ");
                    int qtd = sc.nextInt();

                    carrinho.adicionarItem(catalogo.get(index), qtd);
                }

                case 2 -> {
                    System.out.println("\nSEU CARRINHO:");
                    if (carrinho.getProdutosCarrinho().isEmpty()) {
                        System.out.println("Carrinho vazio!");
                    } else {
                        carrinho.getProdutosCarrinho()
                                .forEach((p, q) ->
                                        System.out.println(p.getNome() + "| qtd: " + q)
                                );
                    }
                }

                case 3 -> {
                    System.out.println("\nSEU CARRINHO:");

                    if (carrinho.getProdutosCarrinho().isEmpty()) {
                        System.out.println("Carrinho vazio!");
                        break;
                    }

                    // transforma as chaves do Map em lista
                    List<Produto> produtos = new ArrayList<>(
                            carrinho.getProdutosCarrinho().keySet()
                    );

                    for (int i = 0; i < produtos.size(); i++) {
                        Produto p = produtos.get(i);
                        int qtdNoCarrinho = carrinho.getProdutosCarrinho().get(p);

                        System.out.printf(
                                "%d. %s - R$ %.2f | qtd: %d%n",
                                i,
                                p.getNome(),
                                p.getPreco(),
                                qtdNoCarrinho
                        );
                    }

                    System.out.print("Digite o número do produto para remover: ");
                    int index = sc.nextInt();

                    if (index < 0 || index >= produtos.size()) {
                        System.out.println("Produto inválido!");
                        break;
                    }

                    System.out.print("Quantidade: ");
                    int qtd = sc.nextInt();

                    Produto produtoSelecionado = produtos.get(index);
                    carrinho.removerItem(produtoSelecionado, qtd);
                }

                case 4 -> {
                    if (carrinho.getProdutosCarrinho().isEmpty()) {
                        System.out.println("Carrinho vazio!");
                        break;
                    }

                    System.out.println("\nFORMA DE PAGAMENTO: 1-PIX, 2-DINHEIRO, 3-CARTÃO");
                    int pg = sc.nextInt();

                    FormaDePagamento forma =
                            (pg == 1) ? FormaDePagamento.PIX :
                                    (pg == 2) ? FormaDePagamento.DINHEIRO :
                                            FormaDePagamento.CARTAO;

                    Pedido pedido = new Pedido(operador, cliente, carrinho, forma);
                    System.out.println(pedido.gerarCupom());

                    opcao = 0;
                }

                case 0 -> System.out.println("Encerrando...");

                default -> System.out.println("Opção inválida!");
            }
        }
        sc.close();
    }
}
