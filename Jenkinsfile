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
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage ('Deploy') {
            steps {
                echo 'Deploy...'
                sh 'mvn clean install'
            }
        }
    }
}