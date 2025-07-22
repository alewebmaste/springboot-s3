FROM openjdk:17-jdk-slim
COPY target/storage-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
