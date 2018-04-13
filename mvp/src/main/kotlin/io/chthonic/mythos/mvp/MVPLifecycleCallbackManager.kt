package io.chthonic.mythos.mvp

import android.util.Log

class MVPLifecycleCallbackManager {
    val dispatcherMap: MutableMap<String, MVPLifecycleCallbackDispatcher> = mutableMapOf()

    fun registerDispatcher(dispatcher: MVPLifecycleCallbackDispatcher) {
        Log.d("MVPLifecycleCallbackManager", "registerDispatcher: dispatcher = $dispatcher")
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
        Log.d("MVPLifecycleCallbackManager", "registerCallback: key = $key, callback = $callback")
        val dispatcher = dispatcherMap[key]
        dispatcher?.registerCallback(key, callback)
    }

    fun unregisterCallback(key: String, callback: MVPLifecycleCallback) {
        val dispatcher = dispatcherMap[key]
        dispatcher?.unregisterCallback(key, callback)
    }
}