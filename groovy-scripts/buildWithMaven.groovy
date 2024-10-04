def call() {
    echo "Building project with Maven..."
    sh '''
    mvn clean install
    echo "Listing all files after Maven build:"
    ls -al
    echo "Maven build completed successfully."
    '''
}
