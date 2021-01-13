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
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
        stage ('Build') {
            steps {
                bat 'mvn install'
            }
        }
        stage ('Deploy') {
            steps {
                bat 'mvn package'
            }
        }
    }
}