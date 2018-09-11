package io.chthonic.mythos.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import io.chthonic.mythos.mvp.Presenter
import io.chthonic.mythos.mvp.PresenterCache
import io.chthonic.mythos.mvp.PresenterCacheBasicLazy
import java.util.concurrent.Callable

/**
 * Created by jhavatar on 7/29/2018.
 */
class PesenterCacheViewModel<P> : ViewModel() where P : Presenter<*> {

    companion object {

        /**
         * Helper function to get PresenterCache from ViewModel associated with parameters activity and uid
         */
        @JvmStatic
        fun <P>getViewModelPresenterCache(activity: FragmentActivity, uid: Int, presenterFactory: () -> P): PresenterCache<P> where P : Presenter<*> {
            return getViewModelPresenterCache(ViewModelProviders.of(activity), uid, presenterFactory)
        }

        @JvmStatic
        fun <P>getViewModelPresenterCache(activity: FragmentActivity, uid: Int, callable: Callable<P>): PresenterCache<P> where P : Presenter<*> {
            return getViewModelPresenterCache(activity, uid, { callable.call() })
        }

        /**
         * Helper function to get PresenterCache from ViewModel associated with parameters fragment and uid
         */
        @JvmStatic
        fun <P>getViewModelPresenterCache(fragment: Fragment, uid: Int, presenterFactory: () -> P): PresenterCache<P> where P : Presenter<*> {
            return getViewModelPresenterCache(ViewModelProviders.of(fragment), uid, presenterFactory)
        }

        @JvmStatic
        fun <P>getViewModelPresenterCache(fragment: Fragment, uid: Int, callable: Callable<P>): PresenterCache<P> where P : Presenter<*> {
            return getViewModelPresenterCache(fragment, uid, { callable.call() })
        }

        /**
         * Helper function to get PresenterCache from ViewModel associated with parameters viewModelProvider and uid
         */
        @JvmStatic
        fun <P>getViewModelPresenterCache(viewModelProvider: ViewModelProvider, uid: Int, presenterFctory: () -> P): PresenterCache<P> where P : Presenter<*> {
            val viewModel = viewModelProvider.get(uid.toString(), PesenterCacheViewModel::class.java) as PesenterCacheViewModel<P>
            return viewModel.cache ?: run {
                val cache = PresenterCacheBasicLazy({ presenterFctory() }, false)
                viewModel.cache = cache
                cache
            }
        }
    }

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