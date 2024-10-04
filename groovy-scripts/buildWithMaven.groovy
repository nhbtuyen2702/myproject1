def call() {
    echo "Building project with Maven..."
    bat '''
    mvn clean install
    dir
    echo "Maven build completed successfully."
    '''
}

return this
