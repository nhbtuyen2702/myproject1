pipeline {
    agent any

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo "Checking out code from repository..."
                checkout scm  // Lấy mã nguồn từ SCM (GitHub/GitLab)
            }
        }

        stage('Set up JDK') {
            steps {
                echo "Setting up JDK 17..."
                sh 'java -version'  // Kiểm tra phiên bản Java
            }
        }

        stage('Build with Maven') {
            steps {
                echo "Building project with Maven..."
                sh 'mvn clean install || echo "Maven build failed"'  // Thực thi lệnh Maven
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
    }
}
