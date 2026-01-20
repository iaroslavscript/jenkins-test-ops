package io.github.iaroslavscript.jenkinstestops


/**
 * Base class for operation factories with registry pattern support.
 * Handles executor registration, caching, and generic creation.
 */
abstract class BaseOperationsFactory implements OperationsFactory {

    protected final Map<class, Closure> executorFactories = [:]
    protected final Map<class, Closure> cachedExecutors = [:]

    /** 
     * Generic method to register a custom executor factory.
     * @param interfaceType The class type of the executor interface.
     * @param factory Closure that creates the executor instance.
     */
    def <T> void register(Class<T> interfaceType, Closure factory) {
        executorFactories[interfaceType] = factory
    }
}

