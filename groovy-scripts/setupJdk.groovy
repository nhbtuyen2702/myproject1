def call() {
    echo "Setting up JDK 17..."
    bat '''
    java -version
    echo "Current working directory: %CD%"
    dir
    echo "JDK 17 setup completed successfully."
    '''
}

return this
