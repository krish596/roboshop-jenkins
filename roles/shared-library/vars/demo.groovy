@Library('roboshop') _
pipeline {
    agent any

    stages {
        stage('Compile') {
            steps {
                echo 'Hello World'
            }
        }

        stage('Test') {
            steps {
                echo 'Hello World'
            }
        }

        stage('Code Quality') {
            steps {
                echo 'Hello World'

                script {
                    log.info 'Starting'
                    log.warning 'Nothing to do!'
                }

            }
        }

        stage('Code Security') {
            steps {
                echo 'Hello World'
            }
        }

        stage('App Deploy') {
            steps {
                echo 'Hello World'
            }
        }
    }
}