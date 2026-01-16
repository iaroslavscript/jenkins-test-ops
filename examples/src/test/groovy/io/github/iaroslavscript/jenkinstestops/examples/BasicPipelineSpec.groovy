package io.github.iaroslavscript.jenkinstestops.examples

import com.lesfurets.jenkins.unit.declarative.DeclarativePipelineTest
import spock.lang.Specification

class BasicPipelineSpec extends Specification {
    // As DeclarativePipelineTest is an abstract class we need to create an anonymous subclass here.
    DeclarativePipelineTest pipelineTest = new DeclarativePipelineTest() {}
    
    def setup() {
        pipelineTest.scriptRoots += 'vars'
        pipelineTest.setUp(pipelineTest)

        // Register allowed methods
        pipelineTest.helper.registerAllowedMethod("sh", [Map]) { Map args ->
            return args.returnStdout ? "mocked output" : null
        }
        
        pipelineTest.helper.registerAllowedMethod("echo", [String]) { String msg ->
            // Mock echo
        }
    }

    def "pipeline test"() {
        setup:
        def script = pipelineTest.loadScript('examples/src/test/groovy/io/github/iaroslavscript/jenkinstestops/examples/BasicPipelineExample.groovy')
        //def script = loadScript('BasicPipelineExample.groovy')
        script.binding.setVariable('env', [:]) // Mock environment variables if needed

        when:
        //script.executePipeline()
        script.call()

        then:
        pipelineTest.printCallStack()
        pipelineTest.assertJobStatusSuccess()
    }
}
