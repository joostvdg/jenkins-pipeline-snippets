pipeline {
    agent none
    stages {
        stage('Init') {
            steps {
                echo 'Init man, does what ever an init can'
            }
        }
        stage('Pipeline') {
            when {
                expression {
                    def originalPipeline = readTrusted 'stage-snippet.groovy'
                    return evaluate(originalPipeline)
                }
            }
            steps {
                echo 'Are done yet?'
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