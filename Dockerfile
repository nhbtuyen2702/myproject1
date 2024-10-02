# Sử dụng OpenJDK 17 (bản slim) làm image cơ sở để chạy ứng dụng Java
# "slim" giúp giảm kích thước image bằng cách chỉ bao gồm các thành phần cần thiết
FROM openjdk:17-jdk-slim

# Sao chép file JAR của ứng dụng từ thư mục 'target' trên máy host vào thư mục '/app' trong container
# File JAR này được tạo ra từ quá trình build bằng Maven (thường nằm trong thư mục target sau khi 'mvn clean install')
COPY target/myproject1-0.0.1-SNAPSHOT.jar /app/myproject1.jar

# Thiết lập thư mục làm việc mặc định cho các lệnh tiếp theo là '/app'
# Mọi lệnh được thực thi trong container sẽ bắt đầu từ thư mục này
WORKDIR /app

# Thiết lập lệnh mặc định để chạy khi container khởi động
# Ở đây, lệnh sẽ là chạy ứng dụng Java với file JAR đã sao chép vào container
ENTRYPOINT ["java", "-jar", "myproject1.jar"]
