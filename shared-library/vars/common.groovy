def compile() {
    if (env.codeType == "python") {
        return "Return as python dont need to Compilation"
    }

    stage('Compile Code'){

        if (env.codeType == "maven") {
            sh '/home/centos/maven/bin/mvn package'
        }

        if (env.codeType == "nodejs") {
            sh 'npm install'
        }



        if (env.codeType == "static") {
            print 'Static'
        }
    }

}

def test() {
    stage('Test Cases') {
        print 'Test'
    }
}

def codeQuality() {
    stage('Code Quality') {
        print 'Code Quality'
    }
}

def codeSecurity() {
    stage('Code Security') {
        print 'Code Security'
    }
}

def release() {
    stage('Release') {
        print 'Release'
    }
}