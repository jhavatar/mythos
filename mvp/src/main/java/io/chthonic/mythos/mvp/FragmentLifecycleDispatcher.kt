package io.chthonic.mythos.mvp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.view.View

/**
 * Interface required by MVPLifecycleCallbackManager for lifecycle event dispatcher.
 * Provide key for each unique lifecycle flow that it supports.
 * When a lifecycle event occurs, for all callbacks registered to lifecycle's key, call corresponding method.
 * @property keyMap maps key to support fragments class that represents the fragment's lifecycle
 */
class FragmentLifecycleDispatcher(val keyMap: Map<Class<out androidx.fragment.app.Fragment>, String>): androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks(), MVPLifecycleCallbackDispatcher {

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

    private fun getKey(fragment: androidx.fragment.app.Fragment?): String? {
        return if (fragment != null) {
            keyMap[fragment::class.java]
        } else null
    }

    private fun getCallbacks(fragment: androidx.fragment.app.Fragment?): Set<MVPLifecycleCallback>? {
        return if (fragment != null) {
            val key = getKey(fragment)
            if (key != null) {
                registrations[key]

            } else {
                null
            }

        } else {
            null
        }
    }


    override fun onFragmentViewCreated(fm: androidx.fragment.app.FragmentManager, f: androidx.fragment.app.Fragment, v: View,
                                       savedInstanceState: Bundle?) {
        val callbacks = getCallbacks(f)
        callbacks?.toList()?.forEach {
            it.onCreated(savedInstanceState)
        }
    }


    override fun onFragmentResumed(fm: androidx.fragment.app.FragmentManager, f: androidx.fragment.app.Fragment) {
        val callbacks = getCallbacks(f)
        callbacks?.toList()?.forEach {
            it.onResumed()
        }
    }


    override fun onFragmentPaused(fm: androidx.fragment.app.FragmentManager, f: androidx.fragment.app.Fragment) {
        val callbacks = getCallbacks(f)
        callbacks?.toList()?.forEach {
            it.onPaused()
        }
    }

    override fun onFragmentViewDestroyed(fm: androidx.fragment.app.FragmentManager, f: androidx.fragment.app.Fragment) {
        val callbacks = getCallbacks(f)
        callbacks?.toList()?.forEach {
            it.onDestroyed()
        }
    }


    override fun onFragmentSaveInstanceState(fm: androidx.fragment.app.FragmentManager, f: androidx.fragment.app.Fragment, outState: Bundle) {
        val callbacks = getCallbacks(f)
        callbacks?.toList()?.forEach {
            it.onSaveInstanceState(outState)
        }
    }


}