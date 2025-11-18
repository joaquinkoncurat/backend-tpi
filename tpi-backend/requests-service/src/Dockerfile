FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY tpi_backend/requests_service/target/requests_service.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
