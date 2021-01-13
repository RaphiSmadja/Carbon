pipeline {
    agent any
    tools {
        maven 'maven3.6.3'
    }
    stages {
        stage ('Initialize') {
            steps {
                bat 'mvn clean'
            }
        }
        stage ('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage ('Build') {
            steps {
                bat 'mvn install'
                bat 'mvn compile'
            }
        }
        stage ('Deploy') {
            steps {
                bat 'mvn package'
            }
        }
    }
}