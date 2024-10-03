pipeline {
    agent any

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
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
                bat 'java -version'  // Sử dụng lệnh 'bat' thay vì 'sh' cho Windows
            }
        }
        stage('Build with Maven') {
            steps {
                echo "Building project with Maven..."
                bat 'mvn clean install'  // Sử dụng lệnh 'bat' cho Windows
            }
        }
        stage('Create Docker Network') {
            steps {
                echo "Creating Docker network..."
                bat 'docker network create myproject-network || echo "Network already exists"'
            }
        }
    }
}
