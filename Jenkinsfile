pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'echo "Git Repo Update detected"'
                sh "pwd"
                dir('frontend') {
                    sh "pwd"
                }
                sh "pwd"
                sh 'touch text.txt'
            }
        }
    }
}