pipeline {
    agent any
    tools {
        maven 'maven3.6.3'
    }
    stages {
        stage ('Build') {
            steps {
                echo 'Build...'
                sh 'mvn install'
                sh 'mvn -B -DskipTests clean package'

            }
        }
        stage ('Test') {
            steps {
                echo 'Test...'
                sh 'mvn test'
            }
        }
    }
}