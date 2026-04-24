# Spring Boot - API Gateway/Spring Cloud Gateway

- In microservices, clients shouldn’t call services directly...

- Instead, use an API Gateway as a single entry point.


➡️ What is an API Gateway?

```
🔹Routes requests to appropriate services
🔹Handles security, logging, and filtering
🔹Simplifies client communication
```
---
➡️ Spring Cloud Gateway

- Spring Cloud Gateway is the Modern Choice to impl API Gateway. It's:

```
🔹Reactive & high performance
🔹Supports routing, filters, and predicates
🔹Built for modern microservices
```
---
➡️ How to Configure Spring Cloud
```
🔹Do Basic Setup (a new spring boot application as gateway server)
🔹Add relevant Routing Configurations
```

Requests to /products/** → routed to Product Service