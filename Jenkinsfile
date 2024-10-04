pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-creds')  // Sử dụng đúng ID của DockerHub đã tạo
    }

    stages {
        stage('Checkout Code') {
            steps {
                script {
                    def checkoutScript = load 'groovy-scripts/checkout.groovy'
                    checkoutScript()  // Gọi trực tiếp mà không cần .call()
                }
            }
        }

        stage('Set up JDK') {
            steps {
                script {
                    def setupJdkScript = load 'groovy-scripts/setupJdk.groovy'
                    setupJdkScript()  // Gọi trực tiếp mà không cần .call()
                }
            }
        }

        stage('Build with Maven') {
            steps {
                script {
                    def buildWithMavenScript = load 'groovy-scripts/buildWithMaven.groovy'
                    buildWithMavenScript()  // Gọi trực tiếp mà không cần .call()
                }
            }
        }

        stage('Login to Docker Hub') {
            steps {
                script {
                    def loginToDockerHubScript = load 'groovy-scripts/loginToDockerHub.groovy'
                    loginToDockerHubScript()  // Gọi trực tiếp mà không cần .call()
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def buildDockerImageScript = load 'groovy-scripts/buildDockerImage.groovy'
                    buildDockerImageScript()  // Gọi trực tiếp mà không cần .call()
                }
            }
        }

        stage('List Docker Images') {
            steps {
                script {
                    def listDockerImagesScript = load 'groovy-scripts/listDockerImages.groovy'
                    listDockerImagesScript()  // Gọi trực tiếp mà không cần .call()
                }
            }
        }

        stage('Create Docker Network') {
            steps {
                script {
                    def createDockerNetworkScript = load 'groovy-scripts/createDockerNetwork.groovy'
                    createDockerNetworkScript()  // Gọi trực tiếp mà không cần .call()
                }
            }
        }

        stage('Run MySQL Container') {
            steps {
                script {
                    def runMysqlContainerScript = load 'groovy-scripts/runMysqlContainer.groovy'
                    runMysqlContainerScript()  // Gọi trực tiếp mà không cần .call()
                }
            }
        }

        stage('Wait for MySQL to be ready') {
            steps {
                script {
                    def waitForMysqlScript = load 'groovy-scripts/waitForMysql.groovy'
                    waitForMysqlScript()  // Gọi trực tiếp mà không cần .call()
                }
            }
        }

        stage('Run Spring Boot Container') {
            steps {
                script {
                    def runSpringBootContainerScript = load 'groovy-scripts/runSpringBootContainer.groovy'
                    runSpringBootContainerScript()  // Gọi trực tiếp mà không cần .call()
                }
            }
        }

        stage('Check Running Docker Containers') {
            steps {
                script {
                    def checkDockerContainersScript = load 'groovy-scripts/checkDockerContainers.groovy'
                    checkDockerContainersScript()  // Gọi trực tiếp mà không cần .call()
                }
            }
        }

        stage('Inspect Docker Containers') {
            steps {
                script {
                    def inspectDockerContainersScript = load 'groovy-scripts/inspectDockerContainers.groovy'
                    inspectDockerContainersScript()  // Gọi trực tiếp mà không cần .call()
                }
            }
        }

        stage('Check Shared Data Between Containers and Host') {
            steps {
                script {
                    def checkSharedDataScript = load 'groovy-scripts/checkSharedData.groovy'
                    checkSharedDataScript()  // Gọi trực tiếp mà không cần .call()
                }
            }
        }

        stage('Check Detailed Spring Boot Logs') {
            steps {
                script {
                    def checkSpringBootLogsScript = load 'groovy-scripts/checkSpringBootLogs.groovy'
                    checkSpringBootLogsScript()  // Gọi trực tiếp mà không cần .call()
                }
            }
        }

        stage('Run Health Check') {
            steps {
                script {
                    def runHealthCheckScript = load 'groovy-scripts/runHealthCheck.groovy'
                    runHealthCheckScript()  // Gọi trực tiếp mà không cần .call()
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
