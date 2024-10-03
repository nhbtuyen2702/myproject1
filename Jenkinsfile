pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-creds')  // Sử dụng đúng ID của DockerHub đã tạo
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo "Checking out code from repository..."
                checkout scm
            }
        }

        stage('Set up JDK') {
            steps {
                echo "Setting up JDK 17..."
                bat 'java -version'  // Sử dụng lệnh 'bat' thay vì 'sh' cho Windows
            }
        }

        stage('Build with Maven') {
            steps {
                echo "Building project with Maven..."
                bat 'mvn clean install'  // Sử dụng lệnh 'bat' cho Windows
            }
        }

        stage('Login to Docker Hub') {
            steps {
                echo "Logging into Docker Hub..."
                script {
                    bat '''
                    docker login -u %DOCKERHUB_CREDENTIALS_USR% -p %DOCKERHUB_CREDENTIALS_PSW%
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building Docker image..."
                bat 'docker build -t myproject1-app:latest .'
            }
        }

        stage('Create Docker Network') {
            steps {
                echo "Creating Docker network..."
                script {
                    bat '''
                    docker network rm myproject-network || echo "No existing network to remove"
                    docker network create myproject-network
                    '''
                }
            }
        }

        // Bước 1: Chạy MySQL container
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
                    '''
                }
            }
        }

        // Bước 2: Đợi MySQL khởi động
        stage('Wait for MySQL to be Ready') {
            steps {
                echo "Waiting for MySQL to be ready..."
                script {
                    bat '''
                    echo "Waiting 30 seconds for MySQL to start..."
                    timeout /T 30
                    '''
                }
            }
        }

        // Bước 3: Chạy Spring Boot container
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
                    '''
                }
            }
        }

        // Kiểm tra trạng thái các container
        stage('Check Running Docker Containers') {
            steps {
                echo "Listing all running Docker containers..."
                script {
                    bat '''
                    docker ps -a
                    '''
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
    }
}
