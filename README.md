# **API de Quizzes**
Este repositório contém a API de um sistema de quizzes desenvolvido com Spring Boot. O objetivo deste projeto é oferecer uma plataforma para a criação, atualização, recuperação e exclusão de quizzes, além de um sistema de autenticação para usuários.

## Funcionalidades:

- Autenticação de Usuário: Login e registro de usuários com JWT.

- Gerenciamento de Quizzes: Criação, leitura, atualização e exclusão de quizzes.

- Sistema de Respostas: Possibilidade de registrar respostas e calcular pontuações.

## Tecnologias Utilizadas:

- Spring Boot - Framework principal utilizado para desenvolver a API.
- Spring Security - Para autenticação e autorização de usuários.
- MongoDB - Banco de dados utilizado para armazenar os dados dos quizzes e usuários.
- JWT (JSON Web Token) - Para autenticação sem estado (stateless).
- Lombok - Para facilitar a criação de getters, setters, construtores e outros.

## Como Executar

Clone o repositório:

- git clone https://github.com/edilsonsantosjr/api-quizzes.git
- Certifique-se de ter o Maven instalado.
- Acesse a API através de http://localhost:8080.

## Endpoints

/auth/login
Método: POST
Descrição: Realiza o login do usuário e retorna um token JWT.
Corpo da Requisição:

json
{
  "email": "email@exemplo.com",
  "password": "senha"
}

/auth/register
Método: POST
Descrição: Realiza o registro de um novo usuário.
Corpo da Requisição:

json
{
  "name": "Nome do Usuário",
  "email": "email@exemplo.com",
  "password": "senha"
}

/users
Método: GET

Descrição: Retorna os dados do usuário autenticado.

Método: PUT

Descrição: Atualiza os dados do usuário autenticado.

Método: DELETE

Descrição: Deleta o usuário autenticado.

/quizzes
Método: GET

Descrição: Retorna todos os quizzes cadastrados.

Método: POST

Descrição: Cria um novo quiz.

Método: PUT

Descrição: Atualiza um quiz existente.

Método: DELETE

Descrição: Deleta um quiz.
