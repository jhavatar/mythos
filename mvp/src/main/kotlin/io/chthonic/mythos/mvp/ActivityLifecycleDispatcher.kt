package io.chthonic.mythos.mvp

import android.app.Activity
import android.app.Application
import android.os.Bundle

class ActivityLifecycleDispatcher(val keyMap: Map<Class<out Activity>, String>): Application.ActivityLifecycleCallbacks, MVPLifecycleCallbackDispatcher {

    override val supportedKeys: Set<String> = keyMap.values.toSet()

    val registrations: MutableMap<String, MutableSet<MVPLifecycleCallback>> = mutableMapOf()

    override fun registerCallback(key: String, callback: MVPLifecycleCallback) {
        val keyRegs = registrations[key] ?: mutableSetOf()
        keyRegs.add(callback)
        registrations[key] = keyRegs
    }

    override fun unregisterCallback(key: String, callback: MVPLifecycleCallback) {
        registrations[key]?.remove(callback)
    }

    override fun clear() {
        registrations.clear()
    }

    private fun getKey(activity: Activity?): String? {
        return if (activity != null) {
            keyMap[activity::class.java]
        } else null
    }

    private fun getCallbacks(activity: Activity?): Set<MVPLifecycleCallback>? {
        return if (activity != null) {
            val key = getKey(activity)
            if (key != null) {
                registrations[key]

            } else {
                null
            }

        } else {
            null
        }
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        val callbacks = getCallbacks(activity)
        callbacks?.toList()?.forEach {
            it.onCreated(savedInstanceState)
        }
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
        val callbacks = getCallbacks(activity)
        callbacks?.toList()?.forEach {
            it.onResumed()
        }
    }

    override fun onActivityPaused(activity: Activity?) {
        val callbacks = getCallbacks(activity)
        callbacks?.toList()?.forEach {
            it.onPaused()
        }
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
        val callbacks = getCallbacks(activity)
        callbacks?.toList()?.forEach {
            it.onDestroyed()
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        val callbacks = getCallbacks(activity)
        callbacks?.toList()?.forEach {
            it.onSaveInstanceState(outState)
        }
    }

}