## Spring Boot - Centralized Configuration with Config Server

- Managing configurations across multiple microservices can get messy...

- Different environments, duplicated properties, hardcoded values…

- That’s where Spring Cloud Config Server helps❗

---
➡️ What is Config Server?
```
🔹Centralized configuration management
🔹Externalizes configs from code
🔹Supports multiple environments (dev, test, prod)
```



---

➡️ Config Server Setup
```java
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApp { }

```

- Microservices fetch configs from Config Server at startup

---
➡️ Server Configuration (Git-based)
```yaml
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/my-org/config-repo
```
---

➡️ Client Configuration

```yaml
spring:
  config:
    import: optional:configserver:http://localhost:8080
```

---