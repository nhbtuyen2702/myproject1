# Sử dụng base image của Java
FROM openjdk:17-jdk-slim

# Tạo thư mục cho ứng dụng trong container
WORKDIR /app

# Copy file JAR từ quá trình build vào thư mục ứng dụng
COPY target/myproject1-0.0.1-SNAPSHOT.jar /app/myproject1.jar

# Cấu hình entrypoint để chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "/app/myproject1.jar"]
