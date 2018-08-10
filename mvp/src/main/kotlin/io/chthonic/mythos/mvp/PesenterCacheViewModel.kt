package io.chthonic.mythos.mvp

import android.arch.lifecycle.ViewModel

/**
 * Created by jhavatar on 7/29/2018.
 */
class PesenterCacheViewModel<PC> : ViewModel() where PC : PresenterCache<*>{

    var cache: PC? = null

    fun start(initCache: PC) {
        cache = initCache
    }

    fun get(): PC? {
        return cache
    }

    override fun onCleared() {
        cache?.clear()
        cache = null
    }
}