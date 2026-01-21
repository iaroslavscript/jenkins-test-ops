package io.github.iaroslavscript.jenkinstestops


/**
 * Base class for operation factories with registry pattern support.
 * Handles executor registration, caching, and generic creation.
 */
abstract class BaseOperationsFactory implements OperationsFactory {

    protected final Map<Class, Closure> executorFactories = [:]
    protected final Map<Class, Closure> cachedExecutors = [:]

    /** 
     * Generic method to register a custom executor factory.
     * @param interfaceType The class type of the executor interface.
     * @param factory Closure that creates the executor instance.
     */
    def <T> void register(Class<T> interfaceType, Closure factory) {
        executorFactories[interfaceType] = factory
    }

    /**
     * Generic method to create or retrieve a cached executor instance.
     * @param interfaceType The class type of the executor interface.
     * @return An instance of the requested executor type.
     */
    def <T> T create(Class<T> interfaceType) {
        if (!executorFactories.containsKey(interfaceType)) {
            throw new IllegalArgumentException("No factory registered for type: ${interfaceType.name}")
        }

        if (!cachedExecutors.containsKey(interfaceType)) {
            cachedExecutors[interfaceType] = executorFactories[interfaceType].call()
        }

        return cachedExecutors[interfaceType] as T
    }
}

