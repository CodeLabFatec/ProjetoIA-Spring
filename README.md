<br id="topo">

<h1 align="center">Projeto IA - Spring</h1>

> **Projeto Integrador 6º Semestre ADS - 2023**

<p align="center">
    <a href="#objetivo">Informações</a> |
    <a href="#tecnologias">Tecnologias</a>
</p>

<span id="objetivo">

## 🚀 Informações

> **Projeto em desenvolvimento**

Esse repositório contém os programas desenvolvidos em Spring para o Projeto IA (Projeto integrador do grupo Codelab da FATEC de São José dos Campos). As outras partes que compõe esse projeto podem ser encontrados no repostório pai [clicando aqui](https://github.com/CodeLabFatec/ProjetoIA).

<br />

> Estratégia de branches e commits

As branches e commits desse projeto seguem o seguinte padrão estabelecido pelo [Conventional Commits](https://www.conventionalcommits.org/pt-br/v1.0.0/) e com exemplos de utilização detalhados [neste repositório](https://github.com/iuricode/padroes-de-commits).

<br>

> Instalação

O arquivo DDL.sql contém um script para gerar todas as tabelas SQL necessárias para o ProjetoIA funcionar, execute-o antes de prosseguir.

Para instalar e executar o projeto siga as instruções abaixo:

1. Clone o repositório:
    ```bash
    git clone https://www.github.com/CodeLabFatec/ProjetoIA-Spring.git
    ```

2. Entre no diretório do projeto:
    ```bash
    cd ProjetoIA-Spring
    ```

3. Instale as dependências:
    ```bash
    mvn install
    ```

4. Crie uma cópia do arquivo `.env.example` chamado `.env`:
    ```bash
    cp .env.example src/main/resources/.env
    ```

5. Abra o arquivo `.env` e insira as URLs do servidor. Por exemplo:
    ```plaintext
    DB_URL=localhost:3306/seu_banco
    DB_USER=seu_usuario
    DB_PASSWORD=sua_senha
    ```

6. Inicie o servidor:
    ```bash
    mvn spring-boot:run
    ```

<br>

<span id="tecnologias">

## 🛠️ Tecnologias

Foram usadas as seguintes ferramentas, linguagens e tecnologias para a execução do projeto:

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)

<br />

→ [Voltar ao topo](#topo)

<br>
