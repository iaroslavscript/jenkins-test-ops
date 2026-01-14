package io.github.iaroslavscript.jenkinstestops.examples

import com.lesfurets.jenkins.unit.declarative.DeclarativePipelineTest
import spock.lang.Specification

class BasicPipelineSpec extends Specification {
    @Delegate DeclarativePipelineTest pipelineTest = new DeclarativePipelineTest()
    def setup() {
        scriptRoots += 'vars'
        setUp()

        // Register allowed methods
        helper.registerAllowedMethod("sh", [Map]) { Map args ->
            return args.returnStdout ? "mocked output" : null
        }
        
        helper.registerAllowedMethod("echo", [String]) { String msg ->
            // Mock echo
        }
    }

    def "pipeline test"() {
        setup:
        def script = loadScript('examples/src/test/groovy/io/github/iaroslavscript/jenkinstestops/examples/BasicPipelineExample.groovy')
        //def script = loadScript('BasicPipelineExample.groovy')
        script.binding.setVariable('env', [:]) // Mock environment variables if needed

        when:
        //script.executePipeline()
        script.call()

        then:
        printCallStack()
        assertJobStatusSuccess()
    }
}
