FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY tpi_backend/api_gateway/target/api-gateway.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
