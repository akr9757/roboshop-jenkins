def call() {
    pipeline {
        agent {
            node {
                label 'workstation'
            }
        }

        options {
            ansiColor('xterm')
        }


        stages {
            stage('code quality') {
                steps {
                    sh 'sonar-scanner -Dsonar.projectkey=${component}'
                }
            }

            stage('unit test cases') {
                steps {
                    sh 'echo unit tests'
                }
            }

            stage('checkmarx sast scan') {
                steps {
                    sh 'echo Checkmarx scan'
                }
            }

            stage('checkmarx sca scan') {
                steps {
                    sh 'checkmarx sca scan'
                }
            }

        }

        post {
            always {
                cleanWs()
            }
        }
    }


}
