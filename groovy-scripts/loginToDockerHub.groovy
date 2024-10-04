def call() {
    echo "Logging into Docker Hub..."
    bat '''
    echo "Logging into Docker Hub with provided credentials..."
    docker login -u %DOCKERHUB_CREDENTIALS_USR% -p %DOCKERHUB_CREDENTIALS_PSW%
    echo "Docker Hub login successful."
    '''
}

return this
