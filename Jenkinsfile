pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building the project...'
                sh './gradlew build -x test'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                sh './gradlew test'
            }
            post {
                always {
                    junit '**/build/test-results/test/*.xml'
                    jacoco(execPattern: '**/build/jacoco/test.exec')
                }
            }
        }
    }
    post {
        always {
            echo 'Pipeline is complete.'
        }
    }
}

