package io.chthonic.mythos.kotlinexample.ui.activities

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import io.chthonic.mythos.kotlinexample.App
import io.chthonic.mythos.kotlinexample.R
import io.chthonic.mythos.kotlinexample.ui.fragments.RoFragment
import io.chthonic.mythos.kotlinexample.ui.presenters.FusPresenter
import io.chthonic.mythos.kotlinexample.ui.vus.FusVu
import io.chthonic.mythos.mvp.*

class FusActivity : MVPActivity<FusPresenter, FusVu>() {

    companion object {
        private val MVP_UID by lazy {
            FusActivity::class.java.simpleName.hashCode()
        }
    }

    private val fragmentLifecycleDispatcher: FragmentLifecycleDispatcher by lazy {
        FragmentLifecycleDispatcher(mapOf(Pair(RoFragment::class.java, resources.getString(R.string.ro_lifecycle_key))))
    }

    override fun createMVPDispatcher(): MVPDispatcher<FusPresenter, FusVu> {
        @Suppress("UNCHECKED_CAST")
        val viewModel = ViewModelProviders.of(this).get(MVP_UID.toString(), PesenterCacheViewModel::class.java)
                as PesenterCacheViewModel<FusPresenter>
        val presenterCache = viewModel.cache ?: run {
            val cache = PresenterCacheBasicLazy<FusPresenter>({ FusPresenter() }, false)
            viewModel.cache = cache
            cache
        }

        return MVPDispatcher (MVP_UID,
                presenterCache
        ) { layoutInflater: LayoutInflater,
            activity: Activity,
            fragment: Fragment?,
            parentView: ViewGroup? ->
            FusVu(layoutInflater, activity, parentView = parentView)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // register fragment lifecycle distpatcher for this activity
        App.lifecycleManager.registerDispatcher(fragmentLifecycleDispatcher)
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleDispatcher, false)
    }

    override fun onDestroy() {
        super.onDestroy()

        // do after super.onDestroy() to still getCached the onDestroy call
        App.lifecycleManager.unregisterDispatcher(fragmentLifecycleDispatcher)
    }

}
