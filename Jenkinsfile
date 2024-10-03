pipeline {
    agent any

    stages {
        stage('Check Jenkinsfile') {
            steps {
                echo 'Jenkinsfile is being read successfully.'
            }
        }
    }

    post {
        always {
            echo 'Jenkinsfile execution completed.'
        }
    }
}
