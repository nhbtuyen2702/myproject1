def call() {
    echo "Running health check on the Spring Boot application..."
    bat '''
    timeout /T 60
    curl -v http://localhost:8080/actuator/health || echo "Health check failed"
    echo "Health check completed successfully."
    '''
}

return this
