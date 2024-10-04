def call() {
    echo "Listing Docker images to verify build..."
    sh '''
    docker images
    echo "Docker images listed successfully."
    '''
}
