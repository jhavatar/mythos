package io.chthonic.mythos.mvp

class MVPLifecycleCallbackManager {
    val dispatcherMap: MutableMap<String, MVPLifecycleCallbackDispatcher> = mutableMapOf()

    fun registerDispatcher(dispatcher: MVPLifecycleCallbackDispatcher) {
        dispatcher.supportedKeys.forEach({
            dispatcherMap[it] = dispatcher
        })
    }

    fun unregisterDispatcher(dispatcher: MVPLifecycleCallbackDispatcher) {
        dispatcher.clear()
        dispatcher.supportedKeys.forEach({
            dispatcherMap.remove(it)
        })
    }

    fun registerCallback(key: String, callback: MVPLifecycleCallback) {
        val dispatcher = dispatcherMap[key]
        dispatcher?.registerCallback(key, callback)
    }

    fun unregisterCallback(key: String, callback: MVPLifecycleCallback) {
        val dispatcher = dispatcherMap[key]
        dispatcher?.unregisterCallback(key, callback)
    }
}