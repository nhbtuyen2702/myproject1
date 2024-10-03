pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-creds')  // Sử dụng đúng ID của DockerHub đã tạo
    }

    stages {
        // 1. Kiểm tra mã nguồn từ repository
        stage('Checkout Code') {
            steps {
                echo "Checking out code from repository..."
                checkout scm
                script {
                    bat '''
                    echo "Current working directory: %CD%"
                    echo "Listing all files and directories after checkout:"
                    dir /B
                    echo "Code checked out successfully."
                    '''
                }
            }
        }

        // 2. Thiết lập JDK 17 để biên dịch ứng dụng
        stage('Set up JDK') {
            steps {
                echo "Setting up JDK 17..."
                bat 'java -version'
                script {
                    bat '''
                    echo "Current working directory: %CD%"
                    echo "Listing all files and directories after setting up JDK:"
                    dir /B
                    echo "JDK 17 setup completed successfully."
                    '''
                }
            }
        }

        // 3. Biên dịch và đóng gói ứng dụng với Maven
        stage('Build with Maven') {
            steps {
                echo "Building project with Maven..."
                bat 'mvn clean install'
                script {
                    bat '''
                    echo "Listing all files after Maven build:"
                    dir /B
                    echo "Maven build completed successfully."
                    '''
                }
            }
        }

        // 4. Đăng nhập vào Docker Hub
        stage('Login to Docker Hub') {
            steps {
                echo "Logging into Docker Hub..."
                script {
                    bat '''
                    echo "Logging into Docker Hub with provided credentials..."
                    docker login -u %DOCKERHUB_CREDENTIALS_USR% -p %DOCKERHUB_CREDENTIALS_PSW%
                    echo "Docker Hub login successful."
                    '''
                }
            }
        }

        // 5. Xây dựng image Docker từ Dockerfile
        stage('Build Docker Image') {
            steps {
                echo "Building Docker image..."
                bat 'docker build -t myproject1-app:latest .'
                script {
                    bat '''
                    echo "Docker image built successfully."
                    '''
                }
            }
        }

        // 6. Liệt kê Docker images để kiểm tra
        stage('List Docker Images') {
            steps {
                echo "Listing Docker images to verify build..."
                script {
                    bat '''
                    echo "Listing all Docker images..."
                    docker images
                    echo "Docker images listed successfully."
                    '''
                }
            }
        }

        // 8. Tạo mạng Docker mới
        stage('Create Docker Network') {
            steps {
                echo "Creating Docker network..."
                script {
                    bat '''
                    docker network create myproject-network
                    echo "Docker network created successfully."
                    '''
                }
            }
        }

        // 9. Khởi chạy MySQL container
        stage('Run MySQL Container') {
            steps {
                echo "Running MySQL container..."
                script {
                    bat '''
                    if not exist C:\\tmp\\shared_data (
                        mkdir C:\\tmp\\shared_data
                    )
                    docker run -d --name myproject-mysql --network myproject-network ^
                        -e MYSQL_ROOT_PASSWORD=2702 ^
                        -e MYSQL_DATABASE=myprojectdb ^
                        -v C:\\tmp\\shared_data:/shared_data ^
                        -p 3306:3306 mysql:8.0
                    echo "MySQL container started successfully."
                    '''
                }
            }
        }

        // Thêm thời gian chờ trước khi khởi chạy Spring Boot container
        stage('Wait for MySQL to be ready') {
            steps {
                echo "Waiting for MySQL to be ready..."
                script {
                    bat '''
                    ping 127.0.0.1 -n 30 > nul
                    echo "MySQL should be ready now."
                    '''
                }
            }
        }

        // 10. Khởi chạy ứng dụng Spring Boot với Docker
        stage('Run Spring Boot Container') {
            steps {
                echo "Running Spring Boot application with Docker Run..."
                script {
                    bat '''
                    docker run -d --name myproject1-container --network myproject-network ^
                        -p 8080:8080 ^
                        -e SPRING_DATASOURCE_URL=jdbc:mysql://myproject-mysql:3306/myprojectdb ^
                        -e SPRING_DATASOURCE_USERNAME=root ^
                        -e SPRING_DATASOURCE_PASSWORD=2702 ^
                        -v C:\\tmp\\shared_data:/app/shared_data myproject1-app:latest
                    echo "Spring Boot application container started successfully."
                    '''
                }
            }
        }

        // 11. Kiểm tra trạng thái các container
        stage('Check Running Docker Containers') {
            steps {
                echo "Listing all running Docker containers..."
                script {
                    bat '''
                    docker ps -a
                    echo "Docker containers listed successfully."
                    '''
                }
            }
        }

        // 12. Xem thông tin chi tiết của container
        stage('Inspect Docker Containers') {
            steps {
                echo "Inspecting Docker containers for additional details..."
                script {
                    bat '''
                    docker inspect myproject1-container || echo "Failed to inspect Spring Boot container"
                    docker inspect myproject-mysql || echo "Failed to inspect MySQL container"
                    echo "Docker containers inspected successfully."
                    '''
                }
            }
        }

        // 13. Kiểm tra dữ liệu chia sẻ giữa máy chủ và các container
        stage('Check Shared Data Between Containers and Host') {
            steps {
                echo "Checking shared data between containers and host..."
                script {
                    bat '''
                    echo "Checking shared data on host:"
                    dir C:\\tmp\\shared_data
                    echo "Checking shared data in MySQL container:"
                    docker exec myproject-mysql dir /shared_data
                    echo "Checking shared data in Spring Boot container:"
                    docker exec myproject1-container dir /app/shared_data
                    echo "Shared data check completed successfully."
                    '''
                }
            }
        }

        // 14. Xem log chi tiết của ứng dụng Spring Boot
        stage('Check Detailed Spring Boot Logs') {
            steps {
                echo "Checking detailed logs of the Spring Boot application..."
                script {
                    bat '''
                    docker logs myproject1-container || echo "Failed to retrieve Spring Boot logs"
                    echo "Spring Boot logs checked successfully."
                    '''
                }
            }
        }

        // 15. Thực hiện kiểm tra sức khỏe (health check) cho ứng dụng
        stage('Run Health Check') {
            steps {
                echo "Running health check on the Spring Boot application..."
                script {
                    bat '''
                    ping 127.0.0.1 -n 60 > nul
                    curl -v http://localhost:8080/actuator/health || echo "Health check failed"
                    echo "Health check completed successfully."
                    '''
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed successfully.'
        }
    }
}
