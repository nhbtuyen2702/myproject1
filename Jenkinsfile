pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
        DOCKERHUB_USERNAME = credentials('0933721593')  // Thay bằng đúng ID của DockerHub
        DOCKERHUB_PASSWORD = credentials('0933721593')  // Thay bằng đúng ID của DockerHub
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
                bat '''
                echo %DOCKERHUB_PASSWORD% | docker login -u %DOCKERHUB_USERNAME% --password-stdin
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building Docker image..."
                bat 'docker build -t myproject1-app:latest .'
            }
        }

        stage('List Docker Images') {
            steps {
                echo "Listing Docker images..."
                bat 'docker images'
            }
        }

        stage('Create Docker Network') {
            steps {
                echo "Creating Docker network..."
                bat 'docker network create myproject-network || echo "Network already exists"'
            }
        }

        stage('Run MySQL Container') {
            steps {
                echo "Running MySQL container..."
                bat '''
                if not exist "C:\\tmp\\shared_data" mkdir C:\\tmp\\shared_data
                docker run -d --name myproject-mysql --network myproject-network ^
                    -e MYSQL_ROOT_PASSWORD=2702 ^
                    -e MYSQL_DATABASE=myprojectdb ^
                    -v C:\\tmp\\shared_data:/shared_data ^
                    -p 3306:3306 mysql:8.0
                '''
            }
        }

        stage('Run Spring Boot Container') {
            steps {
                echo "Running Spring Boot application..."
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

        stage('Check Running Containers') {
            steps {
                echo "Listing all running Docker containers..."
                bat 'docker ps -a'
            }
        }

        stage('Inspect Docker Containers') {
            steps {
                echo "Inspecting Docker containers..."
                bat 'docker inspect myproject1-container || echo "Failed to inspect Spring Boot container"'
                bat 'docker inspect myproject-mysql || echo "Failed to inspect MySQL container"'
            }
        }

        stage('Check Shared Data') {
            steps {
                echo "Checking shared data between containers and host..."
                bat 'dir C:\\tmp\\shared_data'
                bat 'docker exec myproject-mysql dir /shared_data'
                bat 'docker exec myproject1-container dir /app/shared_data'
            }
        }

        stage('Run Health Check') {
            steps {
                echo "Running health check on the Spring Boot application..."
                bat 'ping -n 60 127.0.0.1 >nul && curl -v http://localhost:8080/actuator/health || echo "Health check failed"'
            }
        }
    }

   post {
       always {
           echo "Cleaning up Docker containers..."
           bat 'docker stop myproject1-container myproject-mysql || echo "Containers already stopped"'
           bat 'docker rm myproject1-container myproject-mysql || echo "Containers already removed"'
           bat 'docker network rm myproject-network || echo "Network already removed"'
       }
   }

}
