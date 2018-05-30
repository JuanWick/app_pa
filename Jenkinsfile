pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    parameters {
        string(name: 'IMAGE_REPO_NAME', defaultValue: 'juanwick/app_api', description: '')
        string(name: 'LATEST_BUILD_TAG', defaultValue: 'build-latest', description: '')
        string(name: 'DOCKER_COMPOSE_FILENAME', defaultValue: 'docker-compose.yml', description: '')
        string(name: 'DOCKER_STACK_NAME', defaultValue: 'api_stack', description: '')
        booleanParam(name: 'API_RUN_TEST', defaultValue: true, description: '')
        booleanParam(name: 'PUSH_DOCKER_IMAGES', defaultValue: true, description: '')
        booleanParam(name: 'DOCKER_STACK_RM', defaultValue: false, description: 'Remove previous stack.  This is required if you have updated any secrets or configs as these cannot be updated. ')
    }
    stages {
        stage('api package') {
            steps {
                sh 'mvn package'
            }
        }
    }
}