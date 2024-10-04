def call() {
    echo "Waiting for MySQL to be ready..."
    bat '''
    timeout /T 60
    '''
    echo "MySQL should be ready now."
}

return this
