pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'echo "Git Repo Update detected"'
                sh "echo Start making new Docker image"
                dir('frontend') {
                    sh 'docker build -t runwu_frontend -f DockerFile .'
                }
            }
        }
    }
}