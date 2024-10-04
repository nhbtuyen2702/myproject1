def call() {
    echo "Waiting for MySQL to be ready..."
    sh 'sleep 60'
    echo "MySQL should be ready now."
}
