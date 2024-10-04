def call() {
    echo "Checking shared data between containers and host..."
    sh '''
    echo "Checking shared data on host:"
    ls -al /tmp/shared_data
    echo "Checking shared data in MySQL container:"
    docker exec myproject-mysql ls -al /shared_data
    echo "Checking shared data in Spring Boot container:"
    docker exec myproject1-container ls -al /app/shared_data
    echo "Shared data check completed successfully."
    '''
}
