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
            stage('Code Quality') {
                steps {
                    sh 'ls -l'
                    sh 'sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host.url=http://172.31.46.190:9000 -Dsonar.login=admin -Dsonar.password=admin123'
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
