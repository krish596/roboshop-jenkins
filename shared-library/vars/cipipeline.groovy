
def call() {
    pipeline {
        agent any

        stages {
            stage('Compile Code') {

                steps {
                    sh 'env'
                }
            }

            stage('Test') {
                when {
                    allOf {
                        expression { BRANCH_NAME != null }
                        expression { TAG_NAME == null }
                    }
                }
                steps {
                    echo 'Hello World'
                }
            }

            stage('Code Quality') {
                when {
                    allOf {
                        expression { BRANCH_NAME != null }
                        expression { TAG_NAME == null }
                    }
                }
                steps {
                    echo 'Hello World'

                }
            }

            stage('Code Security') {
                when {
                    expression { BRANCH_NAME == "main" }
                }
                steps {
                    echo 'Hello World'
                }
            }

            stage('Release') {
                when {
                    expression { TAG_NAME ==~ ".*" }
                }
                steps {
                    sh 'env'
                    echo 'Hello World'
                }
            }
        }
    }
}