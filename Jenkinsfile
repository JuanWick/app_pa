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
        stage('Stop Image') {
            steps {
                sh 'cd /'
                sh 'docker-compose down'
//                sh 'docker stop $(docker ps -a -q)' ->> Ajouter une condition
//                sh 'docker rm $(docker ps -a -q)'
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
                    sh 'mvn -DgitHash=$COMMIT_TAG dockerfile:build'
                }
            }
        }
        stage('Push image') {
            environment {
                COMMIT_TAG = sh(returnStdout: true, script: 'git rev-parse HEAD').trim().take(7)
            }
            steps {
                script {
//                    withDockerRegistry([ credentialsId: "docker-hub-credentials", url: "https://registry.hub.docker.com" ]) {
//                        sh 'docker tag juanwick/app_api-$COMMIT_TAG juanwick/app_api:latest'
//                        docker.build().push('latest')
//                    }
                    sh 'docker login -u juanwick -p ihsahn'
                    sh 'docker tag juanwick/app_api-$COMMIT_TAG juanwick/app_api:latest'
                    sh 'docker push juanwick/app_api-$COMMIT_TAG'
                }
            }
        }
        stage('Deploy image') {
            steps {
                sh 'cd /'
                sh 'docker-compose up --build -d'
            }
        }
    }
}
