pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'uname -a'
                sh 'java -version'
            }
        }
        stage('Input') {
            options {
                timeout(time: 10, unit: 'SECONDS')
            }
            input {
                message "Should we continue?"
                ok "Yes, we should."
                submitter "jvandergriendt"
                parameters {
                    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
                }
            }
            steps {
                echo "Hello, ${PERSON}, nice to meet you."
            }
        }
    }
}