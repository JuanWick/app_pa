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
        stage('package') {
            environment {
                COMMIT_TAG = sh(returnStdout: true, script: 'git rev-parse HEAD').trim().take(7)
            }
            steps {
                sh 'echo $COMMIT_TAG'
                sh 'mvn package -DgitHash=$COMMIT_TAG'
            }
        }
        stage('docker build') {
            steps {
                dir('/var/lib/jenkins/workspace/app_pa/app_api') {
                    sh 'pwd'
                    sh 'mvn dockerfile:build'
                }
            }
        }
    }
}