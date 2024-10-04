def call() {
    echo "Listing all running Docker containers..."
    sh '''
    docker ps -a
    echo "Docker containers listed successfully."
    '''
}
