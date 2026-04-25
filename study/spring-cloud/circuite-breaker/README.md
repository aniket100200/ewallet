## Spring Boot - Building Resilient Microservices

- In microservices, failures are inevitable. If one service goes down, it can affect the whole system...

- That’s where different resiliency patters come in...

- Let's try to know about one of them: Circuit Breaker pattern


➡️ What is Circuit Breaker pattern?

A design choice to make microserices resilient, which:
```
🔹Stops repeated calls to a failing service
🔹Prevents system overload
🔹Provides fallback responses
```
➡️ How to Configure?

1️⃣ Modern Choice: Resilience4j

- Resilience4j is the recommended library and modern choice to implement all resilience patterns...

2️⃣ Add Dependency
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-circuitbreaker-resilience4j</artifactId>
</dependency>

<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```


➡️ How It Works
```
🔹Service fails repeatedly → circuit opens
🔹Calls are stopped temporarily
🔹Fallback method is triggered
🔹Once stable → circuit closes again
```

---
## Source Code
```java
@CircuitBreaker(name = "userService",fallbackMethod = "userFallback")
    public JSONObject getUserByUserName(String username){

        // Use the Service ID registered in Eureka instead of localhost:8076
       String url= UriComponentsBuilder.fromHttpUrl(USER_SERVICE_URL+"/get")
               .queryParam("userName",username)
               .toUriString();

        HttpEntity httpEntity = new HttpEntity(new HttpHeaders());
        JSONObject user = restTemplate.exchange(url, HttpMethod.GET,httpEntity, JSONObject.class).getBody();
        return user;

    }

    public JSONObject userFallback(String username,Throwable throwable){
        log.warn("⚠️ User Service is down! Fallback triggered for: {}" , username);
        log.warn("Reason: {}",throwable.getMessage());

        // Better than returning null: Return an empty object or an error object
        // so the calling method doesn't throw a NullPointerException.
        JSONObject fallbackUser = new JSONObject();
        fallbackUser.put("error", "Service Unavailable");
        fallbackUser.put("userName", username);
        fallbackUser.put("status", "FALLBACK");

        return fallbackUser;
    }
```