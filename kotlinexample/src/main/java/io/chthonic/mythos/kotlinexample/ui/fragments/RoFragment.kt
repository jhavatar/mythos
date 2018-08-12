package io.chthonic.mythos.kotlinexample.ui.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import io.chthonic.mythos.kotlinexample.App
import io.chthonic.mythos.kotlinexample.ui.presenters.RoPresenter
import io.chthonic.mythos.kotlinexample.ui.vus.RoVu
import io.chthonic.mythos.mvp.MVPDispatcher
import io.chthonic.mythos.mvp.MVPFragment
import io.chthonic.mythos.mvp.PresenterCacheBasicLazy
import io.chthonic.mythos.viewmodel.PesenterCacheViewModel

/**
 * Created by jhavatar on 3/22/2016.
 */
class RoFragment : MVPFragment<RoPresenter, RoVu>() {

    companion object {
        val TAG: String by lazy {
            RoFragment::class.java.simpleName
        }

        private val MVP_UID by lazy {
            TAG.hashCode()
        }
    }

    private lateinit var  refwatcher: RefWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        refwatcher = LeakCanary.install(App.app)
    }

    override fun createMVPDispatcher(): MVPDispatcher<RoPresenter, RoVu> {
        @Suppress("UNCHECKED_CAST")
        val viewModel = ViewModelProviders.of(this).get(RoFragment.MVP_UID.toString(), PesenterCacheViewModel::class.java)
                as PesenterCacheViewModel<RoPresenter>
        val presenterCache = viewModel.cache ?: run {
            val cache = PresenterCacheBasicLazy<RoPresenter>({ RoPresenter() }, false)
            viewModel.cache = cache
            cache
        }
        return MVPDispatcher(MVP_UID,
                presenterCache,
                ::RoVu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        refwatcher.watch(this)
    }

}