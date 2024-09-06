def compile() {
    if (env.codeType == "maven") {
        sh '/home/centos/maven/bin/mvn package'
    }

    if (env.codeType == "nodejs") {
        print 'NodeJs'
    }

    if (env.codeType == "python") {
        print 'Python'
    }

    if (env.codeType == "static") {
        print 'Static'
    }

}