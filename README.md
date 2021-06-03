# Instruções de execução

Para execução da API é necessária uma base de dados *PostgreSQL* com a extensão `uuid-ossp` instalada.

Configurar no `application.properties` os dados de conexão com a base de dados.

Ao executar a aplicação serão executadas as migrations do *flyway* para criação da estrutura da base de dados.

Pode ser acessado no endereço `http://localhost:8080/swagger-ui/index.html` uma interface gráfica do *Swagger* para testes manuais.
