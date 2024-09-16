def call() {
    pipeline {
        agent any

        parameters {
            string(name: 'COMPONENT', defaultValue: '', description: 'Which Component to Deploy')
            string(name: 'VERSION', defaultValue: '', description: 'Which Version to Deploy')
            string(name: 'ENV', defaultValue: '', description: 'Which Environment to Deploy')
        }

        stages {

            stage('Parametr Store') {
                steps {
                    sh '''
                      aws ssm put-parameter --name "${COMPONENT}.${ENV}.appVersion" --type "String" --value "${VERSION}" --overwrite
'''
                }
            }

            stage('Deploy') {
                steps {
                    sh '''

'''


                }
            }
        }
    }
}