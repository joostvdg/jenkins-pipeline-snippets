pipeline {
    agent any
    stages {
        stage('Init') {
            steps {
                echo 'Init man, does what ever an init can'
            }
        }
        stage('Pipeline') {
            when {
                expression {
                    def exists =  fileExists 'stage-snippet.groovy'
                    return exists
                }
            }
            steps {
                evaluate(readTrusted('stage-snippet.groovy'))
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