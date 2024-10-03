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
                    echo %DOCKERHUB_CREDENTIALS_PSW% | docker login -u %DOCKERHUB_CREDENTIALS_USR% --password-stdin
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
                bat 'docker network create myproject-network || echo "Network already exists"'
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
    }
}
