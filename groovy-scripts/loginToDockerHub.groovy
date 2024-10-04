def call() {
    echo "Logging into Docker Hub..."
    bat '''
    echo "Logging into Docker Hub with provided credentials..."
    echo %DOCKERHUB_CREDENTIALS_PSW% | docker login -u %DOCKERHUB_CREDENTIALS_USR% --password-stdin
    echo "Docker Hub login successful."
    '''
}

return this
