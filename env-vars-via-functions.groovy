pipeline {
    agent any
    options {
        buildDiscarder logRotator(artifactDaysToKeepStr: '5', artifactNumToKeepStr: '5', daysToKeepStr: '5', numToKeepStr: '5')
        durabilityHint 'PERFORMANCE_OPTIMIZED'
        timeout(5)
    }
    libraries {
        lib('joostvdg@master')
    }
    stages {
        stage('Version Bump') {
            when { branch 'master' }
            environment {
                NEW_VERSION = gitNextSemverTagMaven('pom.xml')
            }
            steps {
                container('maven') {
                    sh 'mvn versions:set -DnewVersion=${NEW_VERSION}'
                }
                gitTag("v${NEW_VERSION}")
            }
        }
    }
}