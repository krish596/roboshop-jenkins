def compile() {
    if (env.codeType == "python" || env.codeType == "static") {
        return "Return, Do not need Compilation"
    }

    stage('Compile Code'){

        if (env.codeType == "maven") {
            sh '/home/centos/maven/bin/mvn package'
        }

        if (env.codeType == "nodejs") {
            sh 'npm install'
        }

    }

}

def test() {
    stage('Test Cases') {
        if (env.codeType == "maven") {
            //sh '/home/centos/maven/bin/mvn test'
            print('Ok')
        }

        if (env.codeType == "nodejs") {
            //sh 'npm test'
            print('Ok')
        }

        if (env.codeType == "python") {
            //sh 'python3.6 -m unittest'
            print('Ok')
        }
    }
}

def codeQuality() {
    stage('Code Quality') {
        env.sonaruser = sh (script: 'aws ssm get-parameter --name "sonarqube.user" --with-decryption --query "Parameter.Value" |xargs', returnStdout: true).trim()

        env.sonarpass = sh (script: 'aws ssm get-parameter --name "sonarqube.pass" --with-decryption --query "Parameter.Value" |xargs', returnStdout: true).trim()
        wrap([$class: "MaskPasswordsBuildWrapper", varPasswordPairs: [[password: sonarpass]]]){

            if(env.codeType == "maven") {
                //sh 'sonar-scanner -Dsonar.host.url=http://172.31.1.13:9000 -Dsonar.login=${sonaruser} -Dsonar.password=${sonarpass} -Dsonar.projectKey=${component} -Dsonar.qualitygate.wait=true -Dsonar.java.binaries=./target'
            } else {
                //sh 'sonar-scanner -Dsonar.host.url=http://172.31.1.13:9000 -Dsonar.login=${sonaruser} -Dsonar.password=${sonarpass} -Dsonar.projectKey=${component} -Dsonar.qualitygate.wait=true'
                print 'Ok'
            }


        }

    }
}

def codeSecurity() {
    stage('Code Security') {
        print 'Code Security'

        // In code security we generally used SAST & SCA checks
        // you can say that you are company is using checkmarx SAST and SCA checkmarx for this,
    }
}

def release() {
    stage('Release') {
        env.nexususer = sh (script: 'aws ssm get-parameter --name "nexus.username" --with-decryption --query "Parameter.Value" |xargs', returnStdout: true).trim()

        env.nexuspass = sh (script: 'aws ssm get-parameter --name "nexus.password" --with-decryption --query "Parameter.Value" |xargs', returnStdout: true).trim()
        wrap([$class: "MaskPasswordsBuildWrapper", varPasswordPairs: [[password: nexuspass]]]){

            if(env.codeType == "nodejs") {
                sh 'zip -r ${component}-${TAG_NAME}.zip server.js node_modules'

            }

            if(env.codeType == "maven") {
                sh 'cp target/${component}-1.0.jar ${component}.jar; zip -r ${component}-${TAG_NAME}.zip ${component}.jar'

            }

            sh 'curl -v -u ${nexususer}:${nexuspass} --upload-file ${component}-${TAG_NAME}.zip http://172.31.11.243:8081/repository/${component}/${component}-${TAG_NAME}.zip'
        }
    }
}