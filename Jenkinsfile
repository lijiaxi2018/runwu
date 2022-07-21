pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'echo "Git Repo Update detected"'
                sh 'cd frontend'
                sh 'ls'
                sh 'touch text.txt'
            }
        }
    }
}