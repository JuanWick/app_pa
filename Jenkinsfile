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
                //BUILD_IMAGE_REPO_TAG = "juanwick/app_api:${env.BUILD_TAG}"
            }
            steps {
                dir('/var/lib/jenkins/workspace/app_pa/app_api') {
//                    sh echo("${COMMIT_TAG}")
                    //sh echo("${BUILD_IMAGE_REPO_TAG}")
                    sh pwd
                    sh 'mvn dockerfile:build'

                    //                sh "docker tag $BUILD_IMAGE_REPO_TAG ${params.IMAGE_REPO_NAME}:$COMMIT_TAG"
                    //                sh "docker tag $BUILD_IMAGE_REPO_TAG ${params.IMAGE_REPO_NAME}:${readJSON(file: 'package.json').version}"
                    //                sh "docker tag $BUILD_IMAGE_REPO_TAG ${params.IMAGE_REPO_NAME}:${params.LATEST_BUILD_TAG}"
                    //                sh "docker tag $BUILD_IMAGE_REPO_TAG ${params.IMAGE_REPO_NAME}:$BRANCH_NAME-latest"
                }
            }
        }
    }
}