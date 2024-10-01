FROM openjdk:17-jdk-slim
COPY target/myproject1-0.0.1-SNAPSHOT.jar /app/myproject1.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "myproject1.jar"]
