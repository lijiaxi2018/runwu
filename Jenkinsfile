pipeline {
    agent any
    stages {
        stage('Build') {
            when { branch 'main' }
            steps {
                sh 'echo "Git Repo Update detected"'
                sh 'echo Start making new Docker image'
                dir('frontend') {
                    sh 'docker build -t runwu_frontend -f DockerFile .'

                    sh 'echo Start uploading frontend Docker image'
                    sh 'docker tag runwu_frontend lijiaxi2018/runwu_frontend'
                    sh 'docker push lijiaxi2018/runwu_frontend'
                    sh 'echo Image uploading ended'

                    sh 'echo Delete local image'
                    sh 'docker image rm runwu_frontend'
                    sh 'docker image rm lijiaxi2018/runwu_frontend'
                }

                dir('backend') {
                    sh 'docker build -t runwu_webapi -f DockerFile .'

                    sh 'echo Start uploading backend Docker image'
                    sh 'docker tag runwu_webapi lijiaxi2018/runwu_webapi'
                    sh 'docker push lijiaxi2018/runwu_webapi'
                    sh 'echo Image uploading ended'

                    sh 'echo Delete local image'
                    sh 'docker image rm runwu_webapi'
                    sh 'docker image rm lijiaxi2018/runwu_webapi'
                }
            }
        }
    }
}