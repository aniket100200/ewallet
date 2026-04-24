###  Spring Boot - Service Discovery with Eureka

- In microservices, different services need to find and communicate with each other dynamically...

- Where, Hardcoding URLs is not a good solution obviously, and also not scalable ❌

- That’s where Eureka (Service Discovery) comes in.


➡️ What is Eureka?

```
🔹A service registry where all services register themselves
🔹Other services can discover and communicate without knowing exact locations
```
---
➡️ Steps to integrate Eureka
```
🔹Add dependency
🔹Configure Eureka Server (Registry) - Acts as a central registry
🔹Configure Eureka Client (Service) - Registers itself with Eureka Server & Can discover other services
```

---
➡️ How It Works
```
🔹Services start → register with Eureka
🔹Eureka keeps track of all instances
🔹Services call each other using service names

```
---

# Dependencies and classes

---
## For  Eureka Server
- pom.xml
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.4</version>
        <relativePath/>
    </parent>

    <groupId>com.example.majorproject</groupId>
    <artifactId>eureka-server</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <java.version>21</java.version>
        <spring-cloud.version>2023.0.1</spring-cloud.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
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
            </plugin>
        </plugins>
    </build>
</project>
```

- application
```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

- application.yml
```yaml
server:
  port: 8761

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
  server:
    wait-time-in-ms-when-sync-empty: 0
```
- or application.properties
```properties
server.port: 8761
eureka.client.register-with-eureka: false
eureka.client.fetch-registry: false
server.wait-time-in-ms-when-sync-empty: 0
```

---
## For Eureka Client
- pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <artifactId>ewallet-services</artifactId>
        <groupId>com.example.majorproject</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>notifications-service</artifactId>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <spring-cloud.version>2021.0.8</spring-cloud.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
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

</project>
```
- application
```java
@SpringBootApplication
@EnableEurekaClient
public class EmailApplication {

    public static void main(String[] args) {

        SpringApplication.run(EmailApplication.class, args);
    }
}
```
- - In the latest versions of spring It is Optional.
- application.properties
```properties
# Give the service a unique name for Eureka
spring.application.name=YOUR-SERVICE

# Point it to your Eureka Server
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```


