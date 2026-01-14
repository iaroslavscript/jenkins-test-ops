import io.github.iaroslavscript.jenkinstestops.examples.utils.Utils


def call(Map parameters) {

    Utils utils = new Utils(this)

    pipeline {
        agent any

        stages {
            stage('Build') {
                steps {
                    echo "Hello from Basic Pipeline!"
                    utils.buildProject()
                }
            }
            stage('Test') {
                steps {
                    utils.testProject()
                }
            }
            stage('Deploy') {
                steps {
                    utils.deployProject()
                }
            }
        }
    }
}
