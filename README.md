# Curriculum Vitae App

## Prerequisites

- JDK 17
- Lombok
- WampServer
- Initialize database with `source path/to/cv-init.mysql`

## How to run

- Run database using Winamp

then

1. Run `CvApplication` in IDE

or
1. Execute Gradle task `bootJar`
2. In a command prompt run `java -jar ./build/libs/cv-api-1.0-SNAPSHOT.jar`

App will run by default on http://localhost:8080. Postman can be used for HTTP requests.

## Principles

### General programming principle

- Variables and parameters should be `final` whenever possible
- Parameters should be `@NonNull` whenever possible, null checks should still be performed

### Controller

- When accessing or manipulating a single entity, its id must appear in the URL path, e.g.: `path/entity/id`
- Entities that have a unique attribute can use it instead of the id, e.g.: `path/entity/name`

#### HTTP methods, URI format, JSON body, JSON response and controller methods

| HTTP m. | URI format                         | JSON body                         | JSON response                  | Controller method                   |
|---------|------------------------------------|-----------------------------------|--------------------------------|-------------------------------------|
| PUT     | /path/entity                       | entity as JSON Object, without id | entity as JSON Object          | create(String jsonEntity)           |
| GET     | /path/entity/{id}                  | none                              | entity as JSON Object          | read(String query) or read(Long id) |
| GET     | /path/entity/{uAttr}               | none                              | entity as JSON Object          | read(String query)                  |
| GET     | /path/entity                       | none                              | List of entities as JSON Array | read()                              |
| POST    | /path/entity                       | entity as JSON Object, with id    | entity as JSON Object          | update(String jsonEntry)            |
| DELETE  | /path/entity/{id}                  | none                              | empty JSON Object              | delete(Long id)                     |
| PUT     | /path/entity/{eId}/subEntity/{sId} | none                              | entity as JSON Object          | addSubEntity(Long eId, Long sId)    |
| DELETE  | /path/entity/{eId}/subEntity/{sId} | none                              | entity as JSON Object          | removeSubEntity(Long eId, Long sId) |
