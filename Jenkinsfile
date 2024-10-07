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
                    checkoutScript()
                }
            }
        }

        stage('Set up JDK') {
            steps {
                script {
                    def setupJdkScript = load 'groovy-scripts/setupJdk.groovy'
                    setupJdkScript()
                }
            }
        }

        stage('Build with Maven') {
            steps {
                script {
                    def buildWithMavenScript = load 'groovy-scripts/buildWithMaven.groovy'
                    buildWithMavenScript()
                }
            }
        }

        stage('Login to Docker Hub') {
            steps {
                script {
                    def loginToDockerHubScript = load 'groovy-scripts/loginToDockerHub.groovy'
                    loginToDockerHubScript()
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def buildDockerImageScript = load 'groovy-scripts/buildDockerImage.groovy'
                    buildDockerImageScript()
                }
            }
        }

        stage('List Docker Images') {
            steps {
                script {
                    def listDockerImagesScript = load 'groovy-scripts/listDockerImages.groovy'
                    listDockerImagesScript()
                }
            }
        }

        stage('Create Docker Network') {
            steps {
                script {
                    def createDockerNetworkScript = load 'groovy-scripts/createDockerNetwork.groovy'
                    createDockerNetworkScript()
                }
            }
        }

        stage('Run MySQL Container') {
            steps {
                script {
                    def runMysqlContainerScript = load 'groovy-scripts/runMysqlContainer.groovy'
                    runMysqlContainerScript()
                }
            }
        }

        stage('Wait for MySQL to be ready') {
            steps {
                script {
                    def waitForMysqlScript = load 'groovy-scripts/waitForMysql.groovy'
                    waitForMysqlScript()
                }
            }
        }

        stage('Run Spring Boot Container') {
            steps {
                script {
                    def runSpringBootContainerScript = load 'groovy-scripts/runSpringBootContainer.groovy'
                    runSpringBootContainerScript()
                }
            }
        }

        stage('Check Running Docker Containers') {
            steps {
                script {
                    def checkDockerContainersScript = load 'groovy-scripts/checkDockerContainers.groovy'
                    checkDockerContainersScript()
                }
            }
        }

        stage('Inspect Docker Containers') {
            steps {
                script {
                    def inspectDockerContainersScript = load 'groovy-scripts/inspectDockerContainers.groovy'
                    inspectDockerContainersScript()
                }
            }
        }

        stage('Check Shared Data Between Containers and Host') {
            steps {
                script {
                    def checkSharedDataScript = load 'groovy-scripts/checkSharedData.groovy'
                    checkSharedDataScript()
                }
            }
        }

        stage('Check Detailed Spring Boot Logs') {
            steps {
                script {
                    def checkSpringBootLogsScript = load 'groovy-scripts/checkSpringBootLogs.groovy'
                    checkSpringBootLogsScript()
                }
            }
        }

        stage('Run Health Check') {
            steps {
                script {
                    def runHealthCheckScript = load 'groovy-scripts/runHealthCheck.groovy'
                    runHealthCheckScript()
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
