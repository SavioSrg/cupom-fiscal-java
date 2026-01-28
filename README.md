# Mercado Geral (Java)

Foco em construir uma aplicação de console desenvolvida em Java que simula o fluxo de fechamento de vendas em um supermercado. O projeto demonstra o uso prático de conceitos de POO e manipulação de dados com `Map` e `List`.

## Funcionalidades

O sistema oferece uma interface interativa via terminal para:

- **Catálogo Dinâmico:** Visualização de produtos disponíveis com preços atualizados.

- **Gerenciamento de Carrinho:** Adição e remoção de itens com controle de quantidade.

- **Identificação:** Registro de cliente e funcionário (operador de caixa) no pedido.

- **Cálculo de Descontos:** Lógica automatizada baseada na forma de pagamento:
    - **PIX:** 10% de desconto.
    - **Dinheiro:** 5% de desconto.
    - **Cartão:** Valor integral.

- **Emissão de Cupom Fiscal:** Geração de um resumo detalhado da compra, formatado para o console.

## Estrutura do Projeto

O projeto está organizado em pacotes para garantir a separação de responsabilidades:

- `com.savio.supermercado.app`: Contém as classes principais (`Main` para execução interativa e `Principal` para demonstração).

- `com.savio.supermercado.model`: Classes de entidade como `Produto`, `Carrinho`, `Pedido`, `Cliente` e `Funcionario`.

- `com.savio.supermercado.service`: Interfaces e serviços (ex: `GeradorCodigo`).

## Conceitos de Programação Aplicados

Este projeto foi desenvolvido para fins de estudo e aplica os seguintes pilares:

1. **Herança:** `Cliente` e `Funcionario` estendem a classe `Pessoa`.

2. **Interfaces:** Uso da interface `GeradorCodigo` para padronizar a criação de IDs únicos para produtos e matrículas.

3. **Encapsulamento:** Atributos privados com métodos acessores protegendo o estado dos objetos.

4. **Coleções (Collections Framework):** `HashMap` no carrinho para associar produtos às suas quantidades de forma eficiente.
    - `HashSet` para garantir a unicidade de códigos gerados aleatoriamente.

5. **Enumerações:** `FormaDePagamento` para limitar as opções de checkout e evitar erros de entrada.

## Exemplo de Cupom Fiscal

Ao finalizar uma venda, o sistema gera uma saída formatada como esta:

```
--------------------------------------------------
                  MERCADO GERAL                  
DATA: 28/01/2026 19:45
CLIENTE: Gustavo Chaves
--------------------------------------------------
ITEM                      QTD          VALOR
--------------------------------------------------
Leite Integral            1             4,50
Café Premium              1            15,00
--------------------------------------------------
SUBTOTAL:                             19,50
DESCONTO (PIX):                        1,95
TOTAL:                                17,55
--------------------------------------------------
```
