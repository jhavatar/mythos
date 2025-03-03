package io.chthonic.mythos.mvp

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * Interface required by MVPLifecycleCallbackManager for lifecycle event dispatcher.
 * Provide key for each unique lifecycle flow that it supports.
 * When a lifecycle event occurs, for all callbacks registered to lifecycle's key, call corresponding method.
 * @property keyMap maps key to support Activity class that represents the activity's lifecycle
 */
class ActivityLifecycleDispatcher(val keyMap: Map<Class<out Activity>, String>) :
    Application.ActivityLifecycleCallbacks, MVPLifecycleCallbackDispatcher {

    /**
     *  List of keys that dispatcher supports where each key maps to a unique lifecycle flow.
     */
    override val supportedKeys: Set<String> = keyMap.values.toSet()

    private val registrations: MutableMap<String, MutableSet<MVPLifecycleCallback>> = mutableMapOf()

    /**
     *  Register callback to a lifecycle
     *  @param key key that maps to a lifecycle flow
     *  @param callback type of MVPLifecycleCallback
     */
    override fun registerCallback(key: String, callback: MVPLifecycleCallback) {
        val keyRegs = registrations[key] ?: mutableSetOf()
        keyRegs.add(callback)
        registrations[key] = keyRegs
    }

    /**
     *  Unregister callback to a lifecycle
     *  @param key same key used to register callback
     *  @param callback same callback instance that was used to register
     */
    override fun unregisterCallback(key: String, callback: MVPLifecycleCallback) {
        registrations[key]?.remove(callback)
    }

    /**
     * Remove all registrations
     */
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

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        val callbacks = getCallbacks(activity)
        callbacks?.toList()?.forEach {
            it.onCreated(savedInstanceState)
        }
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
        val callbacks = getCallbacks(activity)
        callbacks?.toList()?.forEach {
            it.onResumed()
        }
    }

    override fun onActivityPaused(activity: Activity) {
        val callbacks = getCallbacks(activity)
        callbacks?.toList()?.forEach {
            it.onPaused()
        }
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        val callbacks = getCallbacks(activity)
        callbacks?.toList()?.forEach {
            it.onDestroyed()
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        val callbacks = getCallbacks(activity)
        callbacks?.toList()?.forEach {
            it.onSaveInstanceState(outState)
        }
    }
}