package io.github.iaroslavscript.jenkinstestops

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach

import static org.assertj.core.api.Assertions.assertThat


class BaseOperationsFactoryTest {

    // Concrete test implementation of abstract class
    private static class TestOperationsFactory extends BaseOperationsFactory {
        // Direct access to protected field for testing
        Map<Class, Closure> getFactories() {
            return this.@executorFactories
        }
    }

    private TestOperationsFactory factory

    @BeforeEach
    void setUp() {
        factory = new TestOperationsFactory()
    }

    @Test
    void testRegisterStoresFactoryInMap() {
        // Given
        def mockFactory = { -> "mock executor" }
        
        // When
        factory.register(String.class, mockFactory)
        
        // Then
        assertThat(factory.getFactories()).containsKey(String.class)
        assertThat(factory.getFactories()[String.class]).isSameAs(mockFactory)
    }

    @Test
    void testRegisterMultipleTypes() {
        // Given
        def stringFactory = { -> "string executor" }
        def integerFactory = { -> "integer executor" }
        
        // When
        factory.register(String.class, stringFactory)
        factory.register(Integer.class, integerFactory)
        
        // Then
        assertThat(factory.getFactories()).hasSize(2)
        assertThat(factory.getFactories()[String.class]).isSameAs(stringFactory)
        assertThat(factory.getFactories()[Integer.class]).isSameAs(integerFactory)
    }
}
