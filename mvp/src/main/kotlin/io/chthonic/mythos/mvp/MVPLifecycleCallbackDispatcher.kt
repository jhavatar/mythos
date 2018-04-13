package io.chthonic.mythos.mvp

interface MVPLifecycleCallbackDispatcher {
    val supportedKeys: Set<String>
    fun registerCallback(key: String, callback: MVPLifecycleCallback)
    fun unregisterCallback(key: String, callback: MVPLifecycleCallback)
    fun clear()
}