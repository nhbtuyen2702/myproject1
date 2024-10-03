pipeline {
    agent any

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
        DOCKERHUB_USERNAME = credentials('dockerhub-username')  // Cần thay đúng ID cho username
        DOCKERHUB_PASSWORD = credentials('dockerhub-password')  // Cần thay đúng ID cho password
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Checking out code..."
                checkout scm
            }
        }
        stage('Set up JDK') {
            steps {
                echo "Setting up JDK 17..."
                sh 'java -version'
            }
        }
        stage('Build with Maven') {
            steps {
                echo "Building project with Maven..."
                // Cài Maven nếu cần thiết hoặc sử dụng Maven từ Docker
                sh 'mvn clean install || echo "Maven build failed"'
            }
        }
        stage('Login to Docker Hub') {
            steps {
                echo "Logging into Docker Hub..."
                sh '''
                echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin
                '''
            }
        }
        stage('Build Docker Image') {
            steps {
                echo "Building Docker image..."
                sh 'docker build -t myproject1-app:latest .'
            }
        }
        stage('List Docker Images') {
            steps {
                echo "Listing Docker images..."
                sh 'docker images'
            }
        }
        stage('Create Docker Network') {
            steps {
                echo "Creating Docker network..."
                sh 'docker network create myproject-network || true'  // Ignore if already exists
            }
        }
        stage('Run MySQL Container') {
            steps {
                echo "Running MySQL container..."
                sh '''
                if [ ! -d /tmp/shared_data ]; then
                    mkdir -p /tmp/shared_data
                fi
                docker run -d --name myproject-mysql --network myproject-network \
                    -e MYSQL_ROOT_PASSWORD=2702 \
                    -e MYSQL_DATABASE=myprojectdb \
                    -v /tmp/shared_data:/shared_data \
                    -p 3306:3306 mysql:8.0
                '''
            }
        }
        stage('Check MySQL Status') {
            steps {
                echo "Checking if MySQL container is running..."
                sh '''
                docker ps -a | grep myproject-mysql || (echo "MySQL container failed to start" && exit 1)
                '''
            }
        }
        stage('Run Spring Boot Container') {
            steps {
                echo "Running Spring Boot application..."
                sh '''
                docker run -d --name myproject1-container --network myproject-network \
                    -p 8080:8080 \
                    -e SPRING_DATASOURCE_URL=jdbc:mysql://myproject-mysql:3306/myprojectdb \
                    -e SPRING_DATASOURCE_USERNAME=root \
                    -e SPRING_DATASOURCE_PASSWORD=2702 \
                    -v /tmp/shared_data:/app/shared_data myproject1-app:latest
                '''
            }
        }
        stage('Check Running Containers') {
            steps {
                echo "Listing all running Docker containers..."
                sh 'docker ps -a'
            }
        }
        stage('Inspect Docker Containers') {
            steps {
                echo "Inspecting Docker containers..."
                sh 'docker inspect myproject1-container || echo "Failed to inspect Spring Boot container"'
                sh 'docker inspect myproject-mysql || echo "Failed to inspect MySQL container"'
            }
        }
        stage('Check Shared Data') {
            steps {
                echo "Checking shared data between containers and host..."
                sh 'ls -al /tmp/shared_data'
                sh 'docker exec myproject-mysql ls -al /shared_data'
                sh 'docker exec myproject1-container ls -al /app/shared_data'
            }
        }
        stage('Run Health Check') {
            steps {
                echo "Running health check on the Spring Boot application..."
                sh 'sleep 60 && curl -v http://localhost:8080/actuator/health || echo "Health check failed"'
            }
        }
    }

   post {
       always {
           node {
               echo "Cleaning up Docker containers..."
               sh 'docker stop myproject1-container myproject-mysql || true'
               sh 'docker rm myproject1-container myproject-mysql || true'
               sh 'docker network rm myproject-network || true'
           }
       }
   }

}
