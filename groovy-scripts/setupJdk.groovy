def setupJdk() {
    echo "Setting up JDK 17..."
    sh '''
    java -version
    echo "Current working directory: $(pwd)"
    echo "Listing all files and directories after setting up JDK:"
    ls -al
    echo "JDK 17 setup completed successfully."
    '''
}

return this