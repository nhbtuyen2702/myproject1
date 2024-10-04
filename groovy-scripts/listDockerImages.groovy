def call() {
    echo "Listing Docker images to verify build..."
    bat '''
    docker images
    echo "Docker images listed successfully."
    '''
}

return this
