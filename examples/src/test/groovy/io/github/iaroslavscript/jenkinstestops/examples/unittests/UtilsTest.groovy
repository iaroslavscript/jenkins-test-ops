package io.github.iaroslavscript.jenkinstestops.examples.unittests

import io.github.iaroslavscript.jenkinstestops.examples.utils.Utils

import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.assertThat

class UtilsTest {

    @Test
    void testBuildProject() {
        def mockScript = [sh: { command -> command }] as Script
        def utils = new Utils(mockScript)
        
        def result = utils.buildProject()
        // Test passes if no exception is thrown
        assertThat(result).isNull()
    }

    @Test
    void testTestProject() {
        def mockScript = [sh: { command -> command }] as Script
        def utils = new Utils(mockScript)
        
        def result = utils.testProject()
        assertThat(result).isNull()
    }

    @Test
    void testDeployProject() {
        def mockScript = [sh: { command -> command }] as Script
        def utils = new Utils(mockScript)
        
        def result = utils.deployProject()
        assertThat(result).isNull()
    }
}

