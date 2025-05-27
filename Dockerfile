
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY target/backend-api-rest-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "app.jar"]