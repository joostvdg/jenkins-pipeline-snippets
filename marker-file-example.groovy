stage('Init') {
    echo 'Does what ever an init does'
}

def originalPipeline = readTrusted 'Jenkinsfile.example'
evaluate originalPipeline


stage('Cleanup') {
    echo 'Doing some cleanup'
}
