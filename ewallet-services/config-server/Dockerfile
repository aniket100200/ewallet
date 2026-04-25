FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Simply copy the jar file that IntelliJ/Maven already built
COPY target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]