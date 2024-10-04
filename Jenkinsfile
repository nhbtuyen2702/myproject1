pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-creds')
    }

    stages {
        stage('Checkout Code') {
            steps {
                load 'groovy-scripts/checkout.groovy'()
            }
        }

        stage('Set up JDK') {
            steps {
                load 'groovy-scripts/setupJdk.groovy'()
            }
        }

        stage('Build with Maven') {
            steps {
                load 'groovy-scripts/buildWithMaven.groovy'()
            }
        }

        stage('Login to Docker Hub') {
            steps {
                load 'groovy-scripts/loginToDockerHub.groovy'()
            }
        }

        stage('Build Docker Image') {
            steps {
                load 'groovy-scripts/buildDockerImage.groovy'()
            }
        }

        stage('List Docker Images') {
            steps {
                load 'groovy-scripts/listDockerImages.groovy'()
            }
        }

        stage('Create Docker Network') {
            steps {
                load 'groovy-scripts/createDockerNetwork.groovy'()
            }
        }

        stage('Run MySQL Container') {
            steps {
                load 'groovy-scripts/runMysqlContainer.groovy'()
            }
        }

        stage('Wait for MySQL to be ready') {
            steps {
                load 'groovy-scripts/waitForMysql.groovy'()
            }
        }

        stage('Run Spring Boot Container') {
            steps {
                load 'groovy-scripts/runSpringBootContainer.groovy'()
            }
        }

        stage('Check Running Docker Containers') {
            steps {
                load 'groovy-scripts/checkDockerContainers.groovy'()
            }
        }

        stage('Inspect Docker Containers') {
            steps {
                load 'groovy-scripts/inspectDockerContainers.groovy'()
            }
        }

        stage('Check Shared Data Between Containers and Host') {
            steps {
                load 'groovy-scripts/checkSharedData.groovy'()
            }
        }

        stage('Check Detailed Spring Boot Logs') {
            steps {
                load 'groovy-scripts/checkSpringBootLogs.groovy'()
            }
        }

        stage('Run Health Check') {
            steps {
                load 'groovy-scripts/runHealthCheck.groovy'()
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed successfully.'
        }
    }
}
