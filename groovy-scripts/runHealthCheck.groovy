def call() {
    echo "Running health check on the Spring Boot application..."
    bat '''
    ping 127.0.0.1 -n 60 > nul
    curl -v http://localhost:8080/actuator/health || echo "Health check failed"
    echo "Health check completed successfully."
    '''
}

return this
