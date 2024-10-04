def call() {
    echo "Listing all running Docker containers..."
    bat '''
    docker ps -a
    echo "Docker containers listed successfully."
    '''
}

return this
