
def call(Map parameters) {
    pipeline {
        agent any

        stages {
            stage('Example Stage') {
                steps {
                    echo "Hello, ${parameters.name}!"
                }
            }
        }
    }
}
