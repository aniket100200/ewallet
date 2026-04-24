# Use a Java 21 runtime (matching your project)
FROM eclipse-temurin:21-jre-jammy

# Set the working directory
WORKDIR /app

# Copy the JAR file from the target folder to the container
# Make sure you run 'mvn clean install' first!
COPY target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]