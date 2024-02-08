pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                script {
                    try {

                        sh './gradlew build'
                    } catch (Exception e) {
                        echo "Build failed, but tests will run next."
                    }
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                sh './gradlew test'
            }
        }
    }
    post {
        always {
            echo 'Pipeline is complete.'
        }
    }
}


