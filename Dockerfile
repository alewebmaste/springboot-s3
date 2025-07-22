FROM openjdk:17-jdk-slim
COPY target/*SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
