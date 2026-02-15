pipeline {
	
	agent any
	
	environment {
        GIT_URL = 'https://github.com/omkarupa/restaurant-service.git' // replace with your Git URL
        BRANCH = 'main'
    }
	
	stages{
		stage('clone Repository'){
			steps{
				git branch : "${BRANCH}", url : "${GIT_URL}"
				
			}
		}
		stage('maven build'){
			steps{
				bat 'mvn clean package'
				
			}
		}
		stage('docker image build'){
			steps{
				bat 'docker build -t restaurant-service:1.0 .'
				
			}
		}
			stage('docker run image in container'){
			steps{
				bat 'docker run -d -p 8091:8091 restaurant-service:1.0'
				
			}
		}
	}
	
}