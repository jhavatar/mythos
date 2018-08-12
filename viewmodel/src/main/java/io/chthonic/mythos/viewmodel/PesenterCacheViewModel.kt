package io.chthonic.mythos.viewmodel

import android.arch.lifecycle.ViewModel
import io.chthonic.mythos.mvp.Presenter
import io.chthonic.mythos.mvp.PresenterCache

/**
 * Created by jhavatar on 7/29/2018.
 */
class PesenterCacheViewModel<P> : ViewModel() where P : Presenter<*> {

    var cache: PresenterCache<P>? = null
        set(value) {
            value?.let{
                assert(!it.mvpDispatcherShouldDestroy) {"mvpDispatcherShouldDestroy should be false for this implementation."}
            }
            field = value
        }

    override fun onCleared() {
        cache?.destroyCached()
        cache = null
    }
}