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

---
## API GATEWAY CONFIG
#### application.properties
```properties
server.port=8080

# Force Reactive mode for Gateway
spring.main.web-application-type=reactive
spring.application.name=API-GATEWAY

# Discovery Locator (Auto-mapping)
spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.discovery.locator.lower-case-service-id=true

# Route 1: User Service
spring.cloud.gateway.routes[0].id=user-service-route
spring.cloud.gateway.routes[0].uri=lb://USER-SERVICE
spring.cloud.gateway.routes[0].predicates[0]=Path=/user/**

# Route 2: Wallet Service
spring.cloud.gateway.routes[1].id=wallet-service-route
spring.cloud.gateway.routes[1].uri=lb://WALLET-SERVICE
spring.cloud.gateway.routes[1].predicates[0]=Path=/wallet/**

# Route 3: Notification Service
spring.cloud.gateway.routes[2].id=notification-service-route
spring.cloud.gateway.routes[2].uri=lb://NOTIFICATION-SERVICE
spring.cloud.gateway.routes[2].predicates[0]=Path=/notification/**

# Route 4: Transaction Service
spring.cloud.gateway.routes[3].id=transaction-service-route
spring.cloud.gateway.routes[3].uri=lb://TRANSACTION-SERVICE
spring.cloud.gateway.routes[3].predicates[0]=Path=/transaction/**,/aniket/**

# Eureka Configuration
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/

# CRITICAL FIX: Add these to ensure the Gateway can talk to others in Docker
eureka.instance.prefer-ip-address=true

```
#### pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.example.majorproject</groupId>
        <artifactId>ewallet-services</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>api-gateway</artifactId>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <spring-cloud.version>2021.0.8</spring-cloud.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <scope>provided</scope>
            <optional>true</optional>
        </dependency>

    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>3.2.0</version>
            </plugin>
        </plugins>
    </build>
</project>
```
---
### Applications.properties in Services
```properties
# ====================================================================
# FOR API GATEWAY CONFIG
# ====================================================================
# Tell the service to register its IP, not its machine name
eureka.instance.prefer-ip-address=true

# This makes the name in Eureka clear and unique
eureka.instance.instance-id=${spring.application.name}:${random.value}
```

