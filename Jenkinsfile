pipeline {
    agent any
    tools {
        maven 'maven3.6.3'
    }
    stages {
        stage ('Build') {
            steps {
                echo 'Build...'
                sh 'mvn clean install'
            }
        }
    }
}