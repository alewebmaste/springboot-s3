FROM openjdk:17-jdk-slim
WORKDIR /app
COPY storage-0.0.1-SNAPSHOT.jar app.jar
CMD java -jar app.jar > /var/log/spring-app.log 2>&1