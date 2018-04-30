package io.chthonic.mythos.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View

class SupportFragmentLifecycleDispatcher(val keyMap: Map<Class<out Fragment>, String>): FragmentManager.FragmentLifecycleCallbacks(), MVPLifecycleCallbackDispatcher {

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

    private fun getKey(fragment: Fragment?): String? {
        return if (fragment != null) {
            keyMap[fragment::class.java]
        } else null
    }

    private fun getCallbacks(fragment: Fragment?): Set<MVPLifecycleCallback>? {
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
        callbacks?.toList()?.forEach {
            it.onCreated(savedInstanceState)
        }
    }


    override fun onFragmentResumed(fm: FragmentManager?, f: Fragment?) {
        val callbacks = getCallbacks(f)
        callbacks?.toList()?.forEach {
            it.onResumed()
        }
    }


    override fun onFragmentPaused(fm: FragmentManager?, f: Fragment?) {
        val callbacks = getCallbacks(f)
        callbacks?.toList()?.forEach {
            it.onPaused()
        }
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager?, f: Fragment?) {
        val callbacks = getCallbacks(f)
        callbacks?.toList()?.forEach {
            it.onDestroyed()
        }
    }


    override fun onFragmentSaveInstanceState(fm: FragmentManager?, f: Fragment?, outState: Bundle?) {
        val callbacks = getCallbacks(f)
        callbacks?.toList()?.forEach {
            it.onSaveInstanceState(outState)
        }
    }


}