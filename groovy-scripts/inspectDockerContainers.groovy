def call() {
    echo "Inspecting Docker containers for additional details..."
    bat '''
    docker inspect myproject1-container || echo "Failed to inspect Spring Boot container"
    docker inspect myproject-mysql || echo "Failed to inspect MySQL container"
    echo "Docker containers inspected successfully."
    '''
}

return this
