def call() {
    echo "Creating Docker network..."
    bat '''
    docker network create myproject-network
    echo "Docker network created successfully."
    '''
}

return this
