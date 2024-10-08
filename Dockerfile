# Build stage
FROM maven:3.8.5-openjdk-17 As build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn dependency:purge-local-repository
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/assignment-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
