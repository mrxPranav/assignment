# Use an official OpenJDK 11 runtime as a parent image
FROM openjdk:11-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven build file and source code to the container
COPY pom.xml ./
COPY src ./src

# Package the application
RUN ./mvnw package -DskipTests

# Copy the built jar file to the container
COPY target/assignment-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which your Spring Boot app runs
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
