package io.github.iaroslavscript.jenkinstestops

/**
 * Factory interface for creating operation objects that abstract external dependencies.
 * This enables dependency injection and makes testing easier by allowing test doubles.
 */
interface OperationsFactory {
    
    /**
     * Generic method to register a custom executor factory.
     * @param interfaceType The class type of the executor interface.
     * @param factory Closure that creates the executor instance.
     */
    def <T> void register(Class<T> interfaceType, Closure factory)
}
