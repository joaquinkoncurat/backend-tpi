FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Expect the module jar to be built before docker build
COPY target/*.jar app.jar

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "app.jar"]
