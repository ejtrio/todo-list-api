# Todo List Api

Simple spring boot application which exposes endpoints of a todo list application.

# Project Layout

Making use of [Rest Repositories](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#howto.data-access.exposing-spring-data-repositories-as-rest) the majority of the code needed can be found in the infrastructure folder and is broken into
models and repositories. RestResource repositories automatically expose all http verbs as endpoints.

# Running the app

Clone this repository and use the gradle bootRun command. The app will start on port 8080.

For additional documentation on the endpoints this application makes use of the hal explorer. After starting the app you can
navigate to http://localhost:8080 in your browser and see the endpoints available with some rest docs for user viewing. 

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.5/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.5/gradle-plugin/reference/html/#build-image)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#howto.data-access.exposing-spring-data-repositories-as-rest)
* [Spring REST Docs](https://docs.spring.io/spring-restdocs/docs/current/reference/html5/)
* [Spring HATEOAS](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#web.spring-hateoas)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)
* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

