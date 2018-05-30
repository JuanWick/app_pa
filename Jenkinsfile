pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    stages {
        stage('package') {
            steps {
                sh 'mvn package'
            }
        }
        stage('docker build') {
             environment {
                COMMIT_TAG = sh(returnStdout: true, script: 'git rev-parse HEAD').trim().take(7)
            }
            steps {
                dir('/var/lib/jenkins/workspace/app_pa/app_api') {
                    sh pwd
                    sh 'mvn dockerfile:build -t ${COMMIT_TAG}'
                }
            }
        }
    }
}