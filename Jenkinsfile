pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    stages {
        stage('api package') {
            steps {
                sh 'mvn package'
            }
        }
        stage('api docker') {
            steps {
                dir('/var/lib/jenkins/workspace/app_pa/app_api') {
                    sh 'mvn dockerfile:build'
                }
            }
        }
    }
}