pipeline {
    agent any
    stages {
        stage('Init') {
            steps {
                echo 'Init man, does what ever an init can'
            }
        }
        stage('Custom Build Step') {
            when {
                expression {
                    def exists =  fileExists 'build-override.groovy'
                    return exists
                }
            }
            steps {
                evaluate(readTrusted('build-override.groovy'))
            }
            post {
                success {
                    echo 'YES'
                }
                failure {
                    echo 'Nooooooo'
                }
                regression {
                    echo 'But, but, it used to work!'
                }
            }
        }
        stage('Cleanup') {
            steps {
                echo 'Cleanup'
            }
        }
    }
}