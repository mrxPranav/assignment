# Build stage
FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn dependency:purge-local-repository
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/assignment-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
