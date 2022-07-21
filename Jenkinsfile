pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'echo "Git Repo Update detected"'
                sh 'echo Start making new Docker image'
                dir('frontend') {
                    sh 'docker build -t runwu_frontend -f DockerFile .'

                    sh 'echo Start uploading Docker image'
                    sh 'docker tag runwu_frontend lijiaxi2018/runwu_frontend'
                    sh 'docker push lijiaxi2018/runwu_frontend'
                }
            }
        }
    }
}