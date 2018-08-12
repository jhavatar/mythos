package io.chthonic.mythos.mvp

import android.arch.lifecycle.ViewModel

/**
 * Created by jhavatar on 7/29/2018.
 */
class PesenterCacheViewModel<P> : ViewModel() where P : Presenter<*>{

    var cache: PresenterCache<P>? = null
        set(value) {
            value?.let{
                assert(!it.mvpDispatcherShouldDestroy, {"mvpDispatcherShouldDestroy should be false for this implementation."})
            }
            field = value
        }

    override fun onCleared() {
        cache?.destroyCached()
        cache = null
    }
}