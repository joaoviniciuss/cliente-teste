# REST Web Service for CRUD operations
[Active] REST web service using Spring JPA, Spring MVC and Spring Boot. 

<p align="center"><img width=95% src="https://github.com/joaoviniciuss/cliente-teste/blob/master/docs/architecture.png"></p>

<!-- In this section add TOC for easy navegation -->
<p align="center">
<b><a href="#requeriments">Requeriments</a></b>
|
<b><a href="#Howtorun">How to run</a></b>
|
<b><a href="#HowtousetheAPI">How to use the API</a></b>
</p>


## Requeriments

* Java - 1.8.x
* JDK or OpenJDK - 1.8.x
* Maven - 3.x.x
* Postgres - 42.x.x
* Spring - 2.0.0.RELEASE (inclued in pom.xml)

## How to run

**1. Clone the repository.**

```bash
git clone https://github.com/joaoviniciuss/cliente-teste.git
```

**2. Create the database "clientes" in Postgres.**

**3. Change postgres username and password as per your installation**

+ open `src/main/resources/application.properties`.

+ change `spring.datasource.username` and `spring.datasource.password` as per your Postgres installation.

```java
spring.datasource.url=jdbc:postgresql://localhost:5432/clientes
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults = false
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL9Dialect
spring.jpa.hibernate.ddl-auto = create
```

**4. Build and run the app using Maven**

```bash
mvn package
java -jar target/cliente-1.0.0.jar
```

+ Also, you can run the app without packaging it using:

```bash
mvn spring-boot:run
```

+ Also, you can run the app without packaging it using:

```bash
./run.sh
```


+ The app will start running at <http://localhost:8080>.
+ The REST service is running at <http://localhost:8080/api/v1.0>.


## How to use the API

+ The current version of the API is v1.0.

1. Creating a new Cliente using POST /api/v1.0/cliente API
+ localhost:8080/api/cliente/
Example: 

```
{
  "firstName": "João Antonio", 
  "lastName":"Silva",
  "address": "Rua A",
  "phone": "434343"
}
```

2. Retrieving all Clientes using GET /api/v1.0/clientes API
+ localhost:8080/api/clientes/

3. Retrieving a single Cliente using GET /api/v1.0/clientes/{clienteId} API
+ localhost:8080/api/clientes/1

4. Updating a Clientes using PUT /api/v1.0/clientes/{clienteId} API
+ localhost:8080/api/clientes/1
Example: 

```
{
  "firstName": "João Antonio", 
  "lastName":"Silva",
  "address": "Rua A",
  "phone": "434343"
}
```

5. Deleting a Cliente using DELETE /api/v1.0/clientes/{clienteId} API
+ localhost:8080/api/clientes/1
