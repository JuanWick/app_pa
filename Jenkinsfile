pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    stages {
        stage('clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('release') {
            steps {
                sh 'mvn release:clean'
                sh 'mvn release:prepare'
                sh 'mvn release:perform'
            }
        }
        stage('docker build') {
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
        stage('docker push') {
            steps {
                sh 'echo("push")'
            }
        }
        stage('docker deploy') {
            steps {
                sh 'echo("deploy")'
            }
        }
    }
}