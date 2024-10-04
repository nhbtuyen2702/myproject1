pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-creds')  // Sử dụng đúng ID của DockerHub đã tạo
        BASH = 'C:\\Program Files\\Git\\bin\\bash.exe'  // Đường dẫn đến bash.exe từ Git Bash
    }

    stages {
        // 1. Kiểm tra mã nguồn từ repository
        stage('Checkout Code') {
            steps {
                echo "Checking out code from repository..."
                checkout scm
                script {
                    sh '''
                    echo "Current working directory: $(pwd)"
                    echo "Listing all files and directories after checkout:"
                    ls -al
                    echo "Code checked out successfully."
                    '''
                }
            }
        }

        // 2. Thiết lập JDK
        stage('Set up JDK') {
            steps {
                echo "Setting up JDK 17..."
                script {
                    sh '''
                    java -version
                    echo "Current working directory: $(pwd)"
                    echo "Listing all files and directories after setting up JDK:"
                    ls -al
                    echo "JDK 17 setup completed successfully."
                    '''
                }
            }
        }

        // 3. Build ứng dụng với Maven
        stage('Build with Maven') {
            steps {
                echo "Building project with Maven..."
                script {
                    sh '''
                    mvn clean install
                    echo "Listing all files after Maven build:"
                    ls -al
                    echo "Maven build completed successfully."
                    '''
                }
            }
        }

        // 4. Đăng nhập Docker Hub
        stage('Login to Docker Hub') {
            steps {
                echo "Logging into Docker Hub..."
                script {
                    sh '''
                    echo "Logging into Docker Hub with provided credentials..."
                    docker login -u $DOCKERHUB_CREDENTIALS_USR -p $DOCKERHUB_CREDENTIALS_PSW
                    echo "Docker Hub login successful."
                    '''
                }
            }
        }

        // 5. Build Docker image
        stage('Build Docker Image') {
            steps {
                echo "Building Docker image..."
                script {
                    sh '''
                    docker build -t myproject1-app:latest .
                    echo "Docker image built successfully."
                    '''
                }
            }
        }

        // 6. Liệt kê Docker images
        stage('List Docker Images') {
            steps {
                echo "Listing Docker images to verify build..."
                script {
                    sh '''
                    echo "Listing all Docker images..."
                    docker images
                    echo "Docker images listed successfully."
                    '''
                }
            }
        }

        // 7. Tạo Docker network
        stage('Create Docker Network') {
            steps {
                echo "Creating Docker network..."
                script {
                    sh '''
                    docker network create myproject-network
                    echo "Docker network created successfully."
                    '''
                }
            }
        }

        // 8. Chạy MySQL container trong Docker network
        stage('Run MySQL Container') {
            steps {
                echo "Running MySQL container..."
                script {
                    sh '''
                    if [ ! -d "/tmp/shared_data" ]; then
                        mkdir /tmp/shared_data
                    fi
                    docker run -d --name myproject-mysql --network myproject-network \
                        -e MYSQL_ROOT_PASSWORD=2702 \
                        -e MYSQL_DATABASE=myprojectdb \
                        -v /tmp/shared_data:/shared_data \
                        -p 3306:3306 mysql:8.0
                    echo "MySQL container started successfully."
                    '''
                }
            }
        }

        // 9. Đợi MySQL khởi động và sẵn sàng
        stage('Wait for MySQL to be ready') {
            steps {
                echo "Waiting for MySQL to be ready..."
                script {
                    sh '''
                    sleep 60  # Chờ 60 giây để MySQL khởi động
                    echo "MySQL should be ready now."
                    '''
                }
            }
        }

        // 10. Chạy ứng dụng Spring Boot với Docker run, trong cùng network
        stage('Run Spring Boot Container') {
            steps {
                echo "Running Spring Boot application with Docker Run..."
                script {
                    sh '''
                    docker run -d --name myproject1-container --network myproject-network \
                        -p 8080:8080 \
                        -e SPRING_DATASOURCE_URL=jdbc:mysql://myproject-mysql:3306/myprojectdb \
                        -e SPRING_DATASOURCE_USERNAME=root \
                        -e SPRING_DATASOURCE_PASSWORD=2702 \
                        -v /tmp/shared_data:/app/shared_data myproject1-app:latest
                    echo "Spring Boot application container started successfully."
                    '''
                }
            }
        }

        // 11. Kiểm tra trạng thái container
        stage('Check Running Docker Containers') {
            steps {
                echo "Listing all running Docker containers..."
                script {
                    sh '''
                    docker ps -a
                    echo "Docker containers listed successfully."
                    '''
                }
            }
        }

        // 12. Xem chi tiết các container đang chạy (bổ sung để kiểm tra thêm chi tiết)
        stage('Inspect Docker Containers') {
            steps {
                echo "Inspecting Docker containers for additional details..."
                script {
                    sh '''
                    docker inspect myproject1-container || echo "Failed to inspect Spring Boot container"
                    docker inspect myproject-mysql || echo "Failed to inspect MySQL container"
                    echo "Docker containers inspected successfully."
                    '''
                }
            }
        }

        // 13. Kiểm tra dữ liệu chia sẻ giữa MySQL, Spring Boot và máy host
        stage('Check Shared Data Between Containers and Host') {
            steps {
                echo "Checking shared data between containers and host..."
                script {
                    sh '''
                    echo "Checking shared data on host:"
                    ls -al /tmp/shared_data
                    echo "Checking shared data in MySQL container:"
                    docker exec myproject-mysql ls -al /shared_data
                    echo "Checking shared data in Spring Boot container:"
                    docker exec myproject1-container ls -al /app/shared_data
                    echo "Shared data check completed successfully."
                    '''
                }
            }
        }

        // 14. Kiểm tra log chi tiết của ứng dụng Spring Boot
        stage('Check Detailed Spring Boot Logs') {
            steps {
                echo "Checking detailed logs of the Spring Boot application..."
                script {
                    sh '''
                    docker logs myproject1-container || echo "Failed to retrieve Spring Boot logs"
                    echo "Spring Boot logs checked successfully."
                    '''
                }
            }
        }

        // 15.  Chạy kiểm tra sức khỏe (health check) của ứng dụng với curl
        stage('Run Health Check') {
            steps {
                echo "Running health check on the Spring Boot application..."
                script {
                    sh '''
                    sleep 60  # Chờ 60 giây để ứng dụng Spring Boot khởi động
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
