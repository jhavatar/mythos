package io.chthonic.mythos.mvp

/**
 * Interface required by MVPLifecycleCallbackManager for lifecycle event dispatcher.
 * Provide key for each unique lifecycle flow that it supports.
 * When a lifecycle event occurs, for all callbacks registered to lifecycle's key, call corresponding method.
 */
interface MVPLifecycleCallbackDispatcher {

    /**
     *  List of keys that dispatcher supports where each key maps to a unique lifecycle flow.
     */
    val supportedKeys: Set<String>

    /**
     *  Register callback to a lifecycle
     *  @param key key that maps to a lifecycle flow
     *  @param callback type of MVPLifecycleCallback
     */
    fun registerCallback(key: String, callback: MVPLifecycleCallback)

    /**
     *  Unregister callback to a lifecycle
     *  @param key same key used to register callback
     *  @param callback same callback instance that was used to register
     */
    fun unregisterCallback(key: String, callback: MVPLifecycleCallback)

    /**
     * Remove all registrations
     */
    fun clear()
}