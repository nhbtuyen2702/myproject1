def call() {
    echo "Running Spring Boot application with Docker Run..."
    sh '''
    docker run -d --name myproject1-container --network myproject-network \
        -p 8080:8080 \
        -e SPRING_DATASOURCE_URL=jdbc:mysql://myproject-mysql:3306/myprojectdb \
        -e SPRING_DATASOURCE_USERNAME=root \
        -e SPRING_DATASOURCE_PASSWORD=2702 \
        -v /tmp/shared_data:/app/shared_data myproject1-app:latest
    echo "Spring Boot application container started successfully."
    '''
}
