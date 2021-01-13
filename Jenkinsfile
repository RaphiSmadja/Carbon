pipeline {
    agent any
    tools {
        maven 'maven3.6.3'
    }
    stages {
        stage ('Build') {
            steps {
                bat echo 'Build...'
                bat 'mvn clean install'
            }
        }
    }
}