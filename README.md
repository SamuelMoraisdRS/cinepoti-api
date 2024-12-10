# Cinepoti

**Cinepoti** é uma API REST para gerenciar informações sobre filmes, séries, diretores, gêneros e avaliações. Este projeto foi criado para permitir fácil integração de dados de entretenimento em aplicativos como catálogos de filmes e sistemas de recomendação.

## Funcionalidades

- **Gerenciamento de Filmes**: Criar, atualizar, deletar e listar filmes.
- **Gêneros**: Criar e associar filmes a gêneros.
- **Avaliações**: Permitir que usuários avaliem filmes e adicionem comentários.
- **Diretores**: Cadastrar e associar filmes a diretores.

## Tecnologias Utilizadas

- **Java 21**: Linguagem de programação.
- **Spring Boot 3.4.0**: Framework para construir a API.
- **Spring Data JPA**: Para interagir com o banco de dados.
- **H2 Database**: Banco de dados em memória para desenvolvimento.
- **Swagger**: Para documentação e testes interativos da API.

## Como Rodar o Projeto

### Pré-requisitos

- **Java 21** ou superior
- **Maven 4.0.0**

## Regras de Commit

Para manter um histórico de commits organizado e claro, adotamos a seguinte convenção de mensagens de commit:

### Padrão de Mensagens de Commit

A mensagem de commit deve seguir o formato abaixo:


### Tipos de Commit

- **feat**: Nova funcionalidade.
- **fix**: Correção de bug.
- **docs**: Atualização de documentação.
- **style**: Alterações no estilo de código (ex. formatação).
- **refactor**: Refatoração de código.
- **test**: Adição ou alteração de testes.
- **chore**: Tarefas de manutenção, como atualizações de dependências ou alterações de configuração.

### Exemplos de Mensagens de Commit

- `feat: Adiciona validação de entrada para filmes`
- `fix: Corrige erro na criação de filme`
- `docs: Atualiza instruções no README`
- `style: Formata código do controller de filmes`
- `refactor: Refatora serviço de avaliação`
- `test: Adiciona testes para o serviço de filmes`

### Endpoints da API
A documentação da API pode ser acessada através do Swagger em:

``` bash
http://localhost:8080/swagger-ui.html


