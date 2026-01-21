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

    /**
     * Generic method to create or retrieve a cached executor instance.
     * @param interfaceType The class type of the executor interface.
     * @return An instance of the requested executor type.
     */
    def <T> T create(Class<T> interfaceType)

    /**
     * Resets the factory's internal state, clearing cached executors.
     */
    void reset()
}
