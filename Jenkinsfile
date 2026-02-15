pipeline {

    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    environment {
        GIT_URL = 'https://github.com/omkarupa/restaurant-service.git'
        BRANCH = 'main'
        COMPOSE_FILE = 'docker-compose.yml'
    }

    stages {

        stage('Clone Repository') {
            steps {
                git branch: "${BRANCH}", url: "${GIT_URL}"
            }
        }

        stage('Build Maven Package') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Containers') {
            steps {
                bat "docker compose -f %COMPOSE_FILE% build"
            }
        }

        stage('Stop Old Containers') {
            steps {
                bat "docker compose -f %COMPOSE_FILE% down"
            }
        }

        stage('Deploy Containers') {
            steps {
                bat "docker compose -f %COMPOSE_FILE% up -d"
            }
        }

        stage('Verify Running Containers') {
            steps {
                bat "docker ps"
            }
        }
    }

    post {
        success {
            echo '✅ Deployment Successful!'
        }
        failure {
            echo '❌ Deployment Failed!'
        }
    }
}
