def call() {
    echo "Checking shared data between containers and host..."
    bat '''
    dir C:\\tmp\\shared_data
    docker exec myproject-mysql dir /shared_data
    docker exec myproject1-container dir /app/shared_data
    echo "Shared data check completed successfully."
    '''
}

return this
