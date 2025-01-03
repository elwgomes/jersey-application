# Jersey Application

*** Para executar o consumidor em Cpp, apenas rode o um ```make``` no terminal ***

### Tecnologias
- Java 17
- Jersey 3.1.2
  - server
  - container-servlet
  - h2k
- Lombok
- JPA/Hibernate
- PostgreSQL (docker)
- Tomcat (docker)
- Maven
- Git (code versioning)

### Execute 
Vá ao diretório raiz da aplicação Java e execute no terminal:
```
$ docker compose up -d
```
para acompanhar o console:
```
$ docker-compose logs -f 
```

Com isso, o docker fará download do PostgreSQL, Maven e do servidor Tomcat, se necessário.<br>
E ele mesmo se encarregará de subir o arquivo <b>.war</b> para a pasta do Tomcat.

### API:
```
http://localhost:8080/jersey/api/users
```

### Documentação:
```
Presente apenas no código através do JavaDOC
```