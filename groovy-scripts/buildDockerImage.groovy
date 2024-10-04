def call() {
    echo "Building Docker image..."
    sh '''
    docker build -t myproject1-app:latest .
    echo "Docker image built successfully."
    '''
}
