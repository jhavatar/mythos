package io.chthonic.mythos.mvp

import android.os.Bundle

interface LifecycleCallback {

    fun onCreated(savedInstance: Bundle?)

    fun onResumed()

    fun onPaused()

    fun onDestroyed()

    fun onSaveInstanceState(outState: Bundle?)
}