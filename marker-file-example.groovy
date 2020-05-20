stage('Init') {
    echo 'Does what ever an init does'
}

def originalPipeline1 = readTrusted 'Jenkinsfile.example'
evaluate originalPipeline1

def originalPipeline2 = readTrusted 'Jenkinsfile.example2'
evaluate originalPipeline2

stage('Cleanup') {
    echo 'Doing some cleanup'
}
