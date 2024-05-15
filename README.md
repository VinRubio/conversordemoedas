# Conversor de Moedas

## Descrição
O **Conversor de Moedas** é uma aplicação em Java que permite a conversão de valores entre diferentes moedas, com uma moeda principal definida. Além de realizar conversões, o programa mantém um histórico das últimas conversões realizadas na sessão sendo executada e, ao sair, gera um arquivo de log com a data, horário e detalhes das conversões.

### Exemplos de Conversão:
- BRL -> USD
- USD -> BRL
- BRL -> EUR
- EUR -> BRL

## Instalação
Para rodar este projeto localmente, siga os passos abaixo:

1. Clone este repositório:
    ```bash
    git clone https://github.com/seu-usuario/conversor-de-moedas.git
    cd conversor-de-moedas
    ```

2. Compile o projeto:
    ```bash
    javac -d bin src/*.java
    ```

3. Execute o projeto:
    ```bash
    java -cp bin Main
    ```

## Uso
Ao executar o programa, você será apresentado a um menu onde poderá escolher as seguintes opções:

1. Opções para Conversão de Moeda
2. Ver Histórico das últimas conversões realizadas na sessão atual do aplicativo
3. Sair
