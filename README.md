# BookStore API

API RESTful para gerenciamento de livros.

## Tecnologias Utilizadas

- Java
- Spring Boot
- H2 Database
- Hibernate Validator
- Maven

## Endpoints

- `POST /api/books`: Salva um novo livro
- `PUT /api/books/{id}`: Atualiza um livro existente
- `DELETE /api/books/{id}`: Deleta um livro existente
- `GET /api/books`: Retorna a lista de todos os livros
- `GET /api/books/{id}`: Retorna um livro específico

## Detalhes dos Endpoints

### `POST /api/books`

Cria um novo livro.

#### Request Body

```
{
    "title": "string",
    "summary": "string",
    "totalPages": integer,
    "releaseDate": "yyyy-MM-dd"
}
```

#### Respostas

- `201 Created`: Livro criado com sucesso.
- `400 Bad Request`: Ocorreu um erro de validação. A resposta conterá informações sobre os campos que falharam na validação.

### `PUT /api/books/{id}`

Atualiza um livro existente.

#### Request Body

```
{
    "title": "string",
    "summary": "string",
    "totalPages": integer,
    "releaseDate": "yyyy-MM-dd"
}
```

#### Respostas

- `200 OK`: Livro atualizado com sucesso.
- `400 Bad Request`: Ocorreu um erro de validação. A resposta conterá informações sobre os campos que falharam na validação.
- `404 Not Found`: O livro com o ID fornecido não foi encontrado.

### `DELETE /api/books/{id}`

Deleta um livro existente.

#### Respostas

- `204 No Content`: Livro deletado com sucesso.
- `404 Not Found`: O livro com o ID fornecido não foi encontrado.

### `GET /api/books`

Retorna a lista de todos os livros.

#### Respostas

- `200 OK`: Lista de livros retornada com sucesso.

### `GET /api/books/{id}`

Retorna um livro específico.

#### Respostas

- `200 OK`: Livro retornado com sucesso.
- `404 Not Found`: O livro com o ID fornecido não foi encontrado.