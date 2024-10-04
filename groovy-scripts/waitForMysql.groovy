def call() {
    echo "Waiting for MySQL to be ready..."
    bat '''
    ping 127.0.0.1 -n 60 > nul
    '''
    echo "MySQL should be ready now."
}

return this
