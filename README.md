## Auth-API
The microservice is used to authenticate users based on Postgres database and return Bearer JwtToken

### Technical description
- Spring Boot
- Spring Authenticated
- PostgresSQL
- JwtToken
- RabbitMQ
- TestContainer for tests

### Available users to authenticated
== **Readonly user** ==
- login: userR
- password: 87654321

== **Read and Write user** ==
- login: userW
- password: 87654321
---
**How to build image**

_Should go to root and run in terminal "docker build"_

**How to start**

_If you want to run microservice with **auth-api** and **beers-api** go to application root and run in terminal "docker-compose up" command._
