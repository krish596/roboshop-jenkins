def call() {
    node('workstation') {
        sh "find . | sed -e '1d' |xargs rm -rf "
        sh 'env'
        git branch: "${ BRANCH_NAME }", url: "https://github.com/krish596/${component}"


        stage('Compile Code') {
            common.compile()
        }

        stage('Test') {
            print 'Hello'
        }

        stage('Code Quality') {
            print 'Hello'
        }

        stage('Code Security') {
            print 'Hello'
        }

        stage('Release') {
            print 'Hello'
        }
    }
}



