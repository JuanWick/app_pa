pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    stages {
        stage('api package') {
            steps {
                echo 'hello'
            }
        }
    }
}