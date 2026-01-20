package io.github.iaroslavscript.jenkinstestops.examples.utils


class Utils implements Serializable {

    protected Script script

    Utils(script) {
        this.script = script
    }

    public void buildProject() {
        script.sh 'make build'
    }

    public void testProject() {
        script.sh 'make test'
    }

    public void deployProject() {
        script.sh 'make deploy'
    }
}
