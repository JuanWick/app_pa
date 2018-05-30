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
                sh 'cd app_api'
                sh 'cd app_api'
                sh 'pwd'
//                sh 'mvn dockerfile:build'
            }
        }
    }
}