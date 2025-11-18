FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy the already-built jar from the module's target folder (build jars with Maven before docker build)
COPY target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
