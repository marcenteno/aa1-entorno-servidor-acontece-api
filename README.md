# Acontece API

## Descripción del proyecto

Acontece API es una API REST desarrollada con Spring Boot para gestionar información relacionada con la organización de eventos.

La aplicación permite administrar usuarios, eventos, categorías, inscripciones y comentarios, almacenando toda la información en una base de datos relacional MariaDB.

El objetivo del proyecto es desarrollar una API que permita realizar operaciones CRUD, búsquedas mediante filtros y controlar los errores que puedan producirse durante las peticiones.

## Tecnologías utilizadas

Para desarrollar el proyecto se han utilizado las siguientes herramientas y tecnologías:

- **Java 25** como lenguaje de programación.
- **Spring Boot 4.1.2** para desarrollar la API REST.
- **Maven** para gestionar las dependencias.
- **MariaDB** como sistema gestor de bases de datos.
- **Spring Data JPA e Hibernate** para trabajar con las entidades y la base de datos.
- **Swagger UI / OpenAPI 3.0.3** para documentar y visualizar los endpoints.
- **Postman** para realizar las pruebas de las operaciones.
- **WireMock** para simular respuestas de la API.
- **Git y GitHub** para el control de versiones del proyecto.

## Modelo de datos

La API está formada por cinco entidades principales:

| Entidad | Descripción |
|---|---|
| Usuario | Gestiona los usuarios de la aplicación. |
| Evento | Contiene la información de los eventos. |
| Categoria | Permite clasificar los eventos. |
| Inscripcion | Gestiona las inscripciones de los usuarios a los eventos. |
| Comentario | Almacena las valoraciones y comentarios sobre los eventos. |

Las entidades están relacionadas entre sí mediante relaciones JPA. Por ejemplo, los eventos tienen un organizador y una categoría, mientras que las inscripciones y los comentarios están asociados a usuarios y eventos.

## Funcionalidades implementadas

La API permite realizar las siguientes operaciones:

- Operaciones CRUD (GET, POST, PUT y DELETE) para las cinco entidades.
- Búsquedas por identificador y otros campos.
- Filtros combinados de hasta tres parámetros en las operaciones GET de cada entidad.
- Validación de los datos recibidos.
- Control de errores mediante respuestas HTTP 400, 404 y 500.
- Documentación de los endpoints mediante OpenAPI 3.0.3.
- Simulación de respuestas mediante WireMock.

## Requisitos previos

Para ejecutar el proyecto es necesario tener instalados:

- JDK 25.
- MariaDB.
- IntelliJ IDEA (o cualquier IDE compatible con Java y Maven).
- Postman, si se quieren probar las peticiones.

## Configuración de la base de datos

Antes de ejecutar la aplicación hay que crear la base de datos en MariaDB.

```sql
CREATE DATABASE eventos_db;
```

Después, hay que revisar el archivo `src/main/resources/application.properties` y configurar los datos de conexión:

```properties
spring.application.name=acontece-api

spring.datasource.url=jdbc:mariadb://localhost:3306/eventos_db
spring.datasource.username=root
spring.datasource.password=TU_CONTRASEÑA
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

Hay que sustituir `TU_CONTRASEÑA` por la contraseña local del usuario de MariaDB.

Hibernate se encarga de actualizar la estructura de las tablas a partir de las entidades del proyecto.

## Cómo ejecutar el proyecto

1. Clonar el repositorio de GitHub o descargar el proyecto.
2. Abrir la carpeta `acontece-api` desde IntelliJ IDEA.
3. Comprobar que está configurado el JDK 25.
4. Crear la base de datos `eventos_db` en MariaDB.
5. Configurar la conexión en `application.properties`.
6. Esperar a que Maven descargue las dependencias.
7. Ejecutar la clase `AconteceApiApplication.java`.

También se puede iniciar desde una terminal situada en la carpeta del proyecto:

**Windows:**

```bash
mvnw.cmd spring-boot:run
```

Una vez iniciada la aplicación, el servidor estará disponible en:

```text
http://localhost:8080
```

## Documentación de la API

La documentación OpenAPI se encuentra en `src/main/resources/static/openapi.yaml`.

Para consultar y probar los endpoints desde el navegador, acceder a:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

También se puede acceder directamente al fichero de especificación:

[http://localhost:8080/openapi.yaml](http://localhost:8080/openapi.yaml)

## Pruebas con Postman

Dentro de la carpeta `Postman` se encuentra la colección exportada:

`Acontece API.postman_collection.json`

Para utilizarla:

1. Abrir Postman.
2. Importar el archivo JSON de la colección.
3. Seleccionar el entorno `Acontece Local` o crear uno con la variable `baseUrl`.
4. Asignar a `baseUrl` el valor `http://localhost:8080`.
5. Ejecutar la colección desde el Runner.

La colección incluye las peticiones y pruebas para comprobar las operaciones desarrolladas en la API.

## Control de versiones

Durante el desarrollo se ha utilizado Git y GitHub para guardar los cambios y organizar el trabajo mediante ramas.

Se han utilizado, entre otras, las siguientes ramas:

- `main`: rama principal del proyecto.
- `desarrollo`: rama de desarrollo.
- `feature/wiremock`: rama utilizada para trabajar con la API Mock de WireMock.

## Estructura del proyecto

```text
acontece-api/
│
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
│
├── Postman/
│   └── Acontece API.postman_collection.json
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── mar/
│   │   │           └── aconteceapi/
│   │   │               ├── controller/
│   │   │               │   ├── UsuarioController.java
│   │   │               │   ├── EventoController.java
│   │   │               │   ├── CategoriaController.java
│   │   │               │   ├── InscripcionController.java
│   │   │               │   └── ComentarioController.java
│   │   │               │
│   │   │               ├── entity/
│   │   │               │   ├── Usuario.java
│   │   │               │   ├── Evento.java
│   │   │               │   ├── Categoria.java
│   │   │               │   ├── Inscripcion.java
│   │   │               │   └── Comentario.java
│   │   │               │
│   │   │               ├── repository/
│   │   │               ├── service/
│   │   │               └── AconteceApiApplication.java
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       │   └── openapi.yaml
│   │       └── application.properties
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── mar/
│                   └── aconteceapi/
│                       └── AconteceApiApplicationTests.java
│
├── .gitignore
├── .gitattributes
├── README.md
├── HELP.md
├── mvnw
├── mvnw.cmd
└── pom.xml
```