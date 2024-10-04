def checkout() {
    echo "Checking out code from repository..."
    checkout scm
    echo "Current working directory: ${pwd()}"
    echo "Code checked out successfully."
}

return this
