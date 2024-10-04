def call() {
    echo "Checking out code from repository..."
    checkout scm
    echo "Current working directory: ${pwd()}"
    bat '''
    dir
    '''
    echo "Code checked out successfully."
}

return this
