package io.github.iaroslavscript.jenkinstestops.examples.unittests

import com.lesfurets.jenkins.unit.declarative.DeclarativePipelineTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static com.lesfurets.jenkins.unit.MethodCall.callArgsToString
import static org.assertj.core.api.Assertions.assertThat


class BasicPipelineTest extends DeclarativePipelineTest {

    Script script

    @BeforeEach
    void setUp() throws Exception {
        scriptRoots += 'vars'
        super.setUp()
        script = loadScript('BasicPipeline.groovy')
    }

    @Test
    void testPipelineAllStagesExecuted() throws Exception {
        script.call([:])

        // Verify that the pipeline executed the expected stages
        assertThat(helper.callStack.findAll { call ->
            call.methodName == 'stage'
        }.collect { call -> callArgsToString(call).replace(', groovy.lang.Closure', '') })
            .containsExactly('Build', 'Test', 'Deploy')

        assertJobStatusSuccess()
    }

    @Test
    void testPipelineMakeWasCalled() throws Exception {
        script.call([:])

        assertThat(helper.callStack.findAll { call ->
            call.methodName == 'sh'
        }.any { call ->
            callArgsToString(call).contains('make build')
        }).isTrue()
    
        assertJobStatusSuccess()
    }
}
