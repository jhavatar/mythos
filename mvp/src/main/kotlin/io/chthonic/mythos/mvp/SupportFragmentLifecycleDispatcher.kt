package io.chthonic.mythos.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.View

class SupportFragmentLifecycleDispatcher(val keyMap: Map<Class<out Fragment>, String>): FragmentManager.FragmentLifecycleCallbacks(), LifecycleCallbackDispatcher {

    companion object {
        val LOG_TAG: String by lazy {
            SupportFragmentLifecycleDispatcher::class.java.simpleName
        }
    }

    override val supportedKeys: Set<String> = keyMap.values.toSet()

    val registrations: MutableMap<String, MutableSet<LifecycleCallback>> = mutableMapOf()

    override fun registerCallback(key: String, callback: LifecycleCallback) {
        val keyRegs = registrations[key] ?: mutableSetOf()
        keyRegs.add(callback)
        registrations[key] = keyRegs
    }

    override fun unregisterCallback(key: String, callback: LifecycleCallback) {
        registrations[key]?.remove(callback)
    }


    override fun clear() {
        registrations.clear()
    }

    private fun getKey(fragment: Fragment?): String? {
        return if (fragment != null) {
            keyMap[fragment::class.java]
        } else null
    }

    private fun getCallbacks(fragment: Fragment?): Set<LifecycleCallback>? {
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


    override fun onFragmentViewCreated(fm: FragmentManager?, f: Fragment?, v: View?,
                              savedInstanceState: Bundle?) {
        val callbacks = getCallbacks(f)
        Log.d(LOG_TAG, "onFragmentViewCreated: callbacks = $callbacks")
        callbacks?.toList()?.forEach {
            it.onCreated(savedInstanceState)
        }
    }


    override fun onFragmentResumed(fm: FragmentManager?, f: Fragment?) {
        val callbacks = getCallbacks(f)
        Log.d(LOG_TAG, "onFragmentResumed: callbacks = $callbacks")
        callbacks?.toList()?.forEach {
            it.onResumed()
        }
    }


    override fun onFragmentPaused(fm: FragmentManager?, f: Fragment?) {
        val callbacks = getCallbacks(f)
        Log.d(LOG_TAG, "onFragmentPaused: callbacks = $callbacks")
        callbacks?.toList()?.forEach {
            it.onPaused()
        }
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager?, f: Fragment?) {
        val callbacks = getCallbacks(f)
        Log.d(LOG_TAG, "onFragmentViewDestroyed: callbacks = $callbacks")
        callbacks?.toList()?.forEach {
            it.onDestroyed()
        }
    }


    override fun onFragmentSaveInstanceState(fm: FragmentManager?, f: Fragment?, outState: Bundle?) {
        val callbacks = getCallbacks(f)
        Log.d(LOG_TAG, "onFragmentSaveInstanceState: callbacks = $callbacks")
        callbacks?.toList()?.forEach {
            it.onSaveInstanceState(outState)
        }
    }


}