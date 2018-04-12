package io.chthonic.mythos.mvp

interface LifecycleCallbackDispatcher {
    val supportedKeys: Set<String>
    fun registerCallback(key: String, callback: LifecycleCallback)
    fun unregisterCallback(key: String, callback: LifecycleCallback)
    fun clear()
}