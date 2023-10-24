No Java Spring Boot, a pasta repository é responsavel por
fazer a comunicação com o banco de dados. Nela ficam as
interfaces que estendem a interface JpaRepository, que
possui metodos para fazer as operações basicas de CRUD
(CREATE, READ, UPDATE, DELETE) no banco de dados.

Para persistir as infos do JPA no banco de daddos psql
é necessario criar uma tabela no banco de dados com o
mesmo nome da classe que extende a interface JpaRepository
e com os mesmos atributos da classe.
