def call() {
    echo "Creating Docker network..."
    sh '''
    docker network create myproject-network
    echo "Docker network created successfully."
    '''
}
