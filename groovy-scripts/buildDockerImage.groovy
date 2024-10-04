def call() {
    echo "Building Docker image..."
    bat '''
    docker build -t myproject1-app:latest .
    echo "Docker image built successfully."
    '''
}

return this
