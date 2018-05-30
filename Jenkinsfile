pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '2'))
    }
    stages {
        stage('Clean package') {
            steps {
                sh 'mvn clean'
//                sh 'mvn release:clean'
            }
        }
        stage('Build package') {
            steps {
                sh 'mvn package'
            }
        }
//        stage('release') {
//            steps {
//                sh 'echo("release")'
////                sh 'mvn release:prepare perform'
//            }
//        }
        stage('Build image') {
            environment {
                COMMIT_TAG = sh(returnStdout: true, script: 'git rev-parse HEAD').trim().take(7)
            }
            steps {
                dir('/var/lib/jenkins/workspace/app_pa/app_api') {
                    sh 'pwd'
                    sh 'echo $COMMIT_TAG'
                    sh 'mvn -DgitHash=$COMMIT_TAG dockerfile:build'
                }
            }
        }
        stage('Push image') {
            environment {
                COMMIT_TAG = sh(returnStdout: true, script: 'git rev-parse HEAD').trim().take(7)
            }
            steps {
                withDockerRegistry([ credentialsId: "docker-hub-credentials", url: "https://registry.hub.docker.com" ]) {
                    sh 'docker login'
                    sh 'docker tag juanwick/app_api-$COMMIT_TAG juanwick/app_api:latest'
                    sh 'docker push juanwick/app_api-$COMMIT_TAG'
                }
            }
        }
//        stage('docker deploy') {
//            steps {
//                sh 'echo("deploy")'
//            }
//        }
    }
}
