# Use the official OpenJDK image as the base image
FROM openjdk:11-jre-slim

# Add a volume to store logs
VOLUME /tmp

# Copy the JAR file
COPY target/demo-heroku-backend.jar app.jar

# Expose port 8080 (or whichever port your Spring Boot app is using)
EXPOSE 8082

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app.jar"]