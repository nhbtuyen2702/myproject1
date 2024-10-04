def call() {
    echo "Checking detailed logs of the Spring Boot application..."
    bat '''
    docker logs myproject1-container || echo "Failed to retrieve Spring Boot logs"
    echo "Spring Boot logs checked successfully."
    '''
}

return this
