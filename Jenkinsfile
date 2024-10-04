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
                script {
                    bat 'checkout.bat'
                }
            }
        }

        stage('Set up JDK') {
            steps {
                echo "Setting up JDK 17..."
                script {
                    bat 'setup_jdk.bat'
                }
            }
        }

        stage('Build with Maven') {
            steps {
                echo "Building project with Maven..."
                script {
                    bat 'build_maven.bat'
                }
            }
        }

        stage('Login to Docker Hub') {
            steps {
                echo "Logging into Docker Hub..."
                script {
                    bat 'login_dockerhub.bat'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building Docker image..."
                script {
                    bat 'build_docker_image.bat'
                }
            }
        }

        stage('List Docker Images') {
            steps {
                echo "Listing Docker images to verify build..."
                script {
                    bat 'list_docker_images.bat'
                }
            }
        }

        stage('Create Docker Network') {
            steps {
                echo "Creating Docker network..."
                script {
                    bat 'create_docker_network.bat'
                }
            }
        }

        stage('Run MySQL Container') {
            steps {
                echo "Running MySQL container..."
                script {
                    bat 'run_mysql_container.bat'
                }
            }
        }

        stage('Wait for MySQL to be ready') {
            steps {
                echo "Waiting for MySQL to be ready..."
                script {
                    bat 'wait_for_mysql.bat'
                }
            }
        }

        stage('Run Spring Boot Container') {
            steps {
                echo "Running Spring Boot application with Docker Run..."
                script {
                    bat 'run_spring_boot_container.bat'
                }
            }
        }

        stage('Check Running Docker Containers') {
            steps {
                echo "Listing all running Docker containers..."
                script {
                    bat 'check_docker_containers.bat'
                }
            }
        }

        stage('Inspect Docker Containers') {
            steps {
                echo "Inspecting Docker containers for additional details..."
                script {
                    bat 'inspect_docker_containers.bat'
                }
            }
        }

        stage('Check Shared Data Between Containers and Host') {
            steps {
                echo "Checking shared data between containers and host..."
                script {
                    bat 'check_shared_data.bat'
                }
            }
        }

        stage('Check Detailed Spring Boot Logs') {
            steps {
                echo "Checking detailed logs of the Spring Boot application..."
                script {
                    bat 'check_spring_boot_logs.bat'
                }
            }
        }

        stage('Run Health Check') {
            steps {
                echo "Running health check on the Spring Boot application..."
                script {
                    bat 'run_health_check.bat'
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
