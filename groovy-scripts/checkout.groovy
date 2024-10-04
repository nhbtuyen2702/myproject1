def checkout() {
    echo "Checking out code from repository..."
    checkout scm
    echo "Current working directory: ${pwd()}"
    sh 'ls -al'
    echo "Code checked out successfully."
}

return this
