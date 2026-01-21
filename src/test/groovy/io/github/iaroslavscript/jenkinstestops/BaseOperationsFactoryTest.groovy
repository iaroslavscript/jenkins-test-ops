package io.github.iaroslavscript.jenkinstestops

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy


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

    @Test
    void testCreateReturnsExecutorFromFactory() {
        // Given
        def mockExecutor = "test executor"
        def mockFactory = { -> mockExecutor }
        factory.register(String.class, mockFactory)
        
        // When
        def result = factory.create(String.class)
        
        // Then
        assertThat(result).isSameAs(mockExecutor)
    }

    @Test
    void testCreateCachesExecutor() {
        // Given
        def mockFactory = { -> "cached executor" }
        factory.register(String.class, mockFactory)
        
        // When
        def result1 = factory.create(String.class)
        def result2 = factory.create(String.class)
        
        // Then
        assertThat(result1).isSameAs(result2)
    }

    @Test
    void testCreateThrowsExceptionForUnregisteredType() {
        // When/Then
        assertThatThrownBy({ factory.create(String.class) })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("No factory registered for type: java.lang.String")
    }

    @Test
    void testRegisterAndCreateMultipleDifferentClasses() {
        // Given
        def stringExecutor = "string executor"
        def integerExecutor = 42
        def listExecutor = ["item1", "item2"]
        
        factory.register(String.class, { -> stringExecutor })
        factory.register(Integer.class, { -> integerExecutor })
        factory.register(List.class, { -> listExecutor })
        
        // When
        def stringResult = factory.create(String.class)
        def integerResult = factory.create(Integer.class)
        def listResult = factory.create(List.class)
        
        // Then
        assertThat(stringResult).isSameAs(stringExecutor)
        assertThat(integerResult).isSameAs(integerExecutor)
        assertThat(listResult).isSameAs(listExecutor)
    }
}
