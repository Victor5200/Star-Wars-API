# Star-Wars-API

Documentação das API´s.

- Para buscar a lista de planetas ou um determinado planeta, seja pelo identificador dele ou pelo nome, temos disponivel as api´s abaixo:

```http
GET /planetas/
GET /planetas/{id}
GET /planetas/nome/{nome}
```

- Para salvar ou alterar um novo planeta temos as API´s abaixo disponiveis.

```http
POST /planetas/
PUT /planetas/{id}
```
- Para deletar um determinado Planeta:

```http
DELETE /planetas/{id}
```
