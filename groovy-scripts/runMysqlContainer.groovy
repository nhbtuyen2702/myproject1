def call() {
    echo "Running MySQL container..."
    bat '''
    if not exist C:\\tmp\\shared_data (
        mkdir C:\\tmp\\shared_data
    )
    docker run -d --name myproject-mysql --network myproject-network ^
        -e MYSQL_ROOT_PASSWORD=2702 ^
        -e MYSQL_DATABASE=myprojectdb ^
        -v C:\\tmp\\shared_data:/shared_data ^
        -p 3306:3306 mysql:8.0
    echo "MySQL container started successfully."
    '''
}

return this
