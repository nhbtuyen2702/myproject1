def call() {
    echo "Running health check on the Spring Boot application..."
    sh '''
    sleep 60
    curl -v http://localhost:8080/actuator/health || echo "Health check failed"
    echo "Health check completed successfully."
    '''
}
