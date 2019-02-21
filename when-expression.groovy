pipeline {
    agent any
    stages {
        stage('Version Bump') {
            when {
                expression {
                    NEW_VERSION = gitNextSemverTagMaven('pom.xml')
                    return env.BRANCH_NAME == 'master' &&  NEW_VERSION != ''
                }
            }
            steps {
                sh "mvn versions:set -DnewVersion=${NEW_VERSION}"
                gitTag("v${NEW_VERSION}")
            }
        }
    }
}