package io.chthonic.mythos.mvp

/**
 * Coordinates the registering of lifecycle listeners and dispatchers.
 * Connects listeners with dispatchers that manage the listeners desired lifecycle.
 */
class MVPLifecycleCallbackManager {
    val dispatcherMap: MutableMap<String, MVPLifecycleCallbackDispatcher> = mutableMapOf()

    /**
     * Register a lifecycle callback dispatcher.
     * Note, the dispatcher contains the info of which keys it supports
     * @param dispatcher type of MVPLifecycleCallbackDispatcher expected
     */
    fun registerDispatcher(dispatcher: MVPLifecycleCallbackDispatcher) {
        dispatcher.supportedKeys.forEach({
            dispatcherMap[it] = dispatcher
        })
    }

    /**
     * Unregister a lifecycle callback dispatcher.
     * @param dispatcher same instance expected that was used to register
     */
    fun unregisterDispatcher(dispatcher: MVPLifecycleCallbackDispatcher) {
        dispatcher.clear()
        dispatcher.supportedKeys.forEach({
            dispatcherMap.remove(it)
        })
    }

    /**
     * Register a lifecycle callback to a lifecyle
     * @param key the key references a lifecycle that will trigger the callbacks. A previously registered dispatcher should have supported the key.
     * @param callback type of MVPLifecycleCallback expected
     */
    fun registerCallback(key: String, callback: MVPLifecycleCallback) {
        val dispatcher = dispatcherMap[key]
        dispatcher?.registerCallback(key, callback)
    }

    /**
     * Unregister a lifecycle callback from a dispatcher's events.
     * @param key same key that callback was registered on
     * @param callback same instance expected that was used to register
     */
    fun unregisterCallback(key: String, callback: MVPLifecycleCallback) {
        val dispatcher = dispatcherMap[key]
        dispatcher?.unregisterCallback(key, callback)
    }
}