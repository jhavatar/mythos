package io.chthonic.mythos.mvp

import android.os.Bundle

interface MVPLifecycleCallback {

    fun onCreated(savedInstance: Bundle?)

    fun onResumed()

    fun onPaused()

    fun onDestroyed()

    fun onSaveInstanceState(outState: Bundle?)
}