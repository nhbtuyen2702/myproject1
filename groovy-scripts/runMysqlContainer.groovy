def call() {
    echo "Running MySQL container..."
    sh '''
    if [ ! -d "/tmp/shared_data" ]; then
        mkdir /tmp/shared_data
    fi
    docker run -d --name myproject-mysql --network myproject-network \
        -e MYSQL_ROOT_PASSWORD=2702 \
        -e MYSQL_DATABASE=myprojectdb \
        -v /tmp/shared_data:/shared_data \
        -p 3306:3306 mysql:8.0
    echo "MySQL container started successfully."
    '''
}
