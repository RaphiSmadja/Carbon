pipeline {
    agent any
    stages {
            stage('Build') {
                agent any
                steps {
                    checkout scm
                    sh 'make'
                    stash includes: '**/target/*.jar', name: 'app'
                }
            }
            stage('Test') {
                agent {
                    label 'windows'
                }
                steps {
                    unstash 'app'
                    bat 'make check'
                }
                post {
                    always {
                        junit '**/target/*.xml'
                    }
                }
            }
            stage('Deploy') {
                steps {
                    echo 'Deploy  !'
                }
            }
    }