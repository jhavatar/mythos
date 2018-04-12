package io.chthonic.mythos.mvp

class MVPLifecycleCallbackManager {
    val dispatcherMap: MutableMap<String, LifecycleCallbackDispatcher> = mutableMapOf()

    fun registerDispatcher(dispatcher: LifecycleCallbackDispatcher) {
        dispatcher.supportedKeys.forEach({
            dispatcherMap[it] = dispatcher
        })
    }

    fun unregisterDispatcher(dispatcher: LifecycleCallbackDispatcher) {
        dispatcher.clear()
        dispatcher.supportedKeys.forEach({
            dispatcherMap.remove(it)
        })
    }

    fun registerCallback(key: String, callback: LifecycleCallback) {
        val dispatcher = dispatcherMap[key]
        dispatcher?.registerCallback(key, callback)
    }

    fun unregisterCallback(key: String, callback: LifecycleCallback) {
        val dispatcher = dispatcherMap[key]
        dispatcher?.unregisterCallback(key, callback)
    }
}