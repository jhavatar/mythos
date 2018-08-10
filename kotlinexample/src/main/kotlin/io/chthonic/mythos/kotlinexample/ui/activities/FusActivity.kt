package io.chthonic.mythos.kotlinexample.ui.activities

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
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

    private val fragmentLifecycleDispatcher: SupportFragmentLifecycleDispatcher by lazy {
        SupportFragmentLifecycleDispatcher(mapOf(Pair(RoFragment::class.java, resources.getString(R.string.ro_lifecycle_key))))
    }

    override fun createMVPDispatcher(): MVPDispatcher<FusPresenter, FusVu> {
//        ViewModelProviders.of(this)
//        val type = TypeToken<PesenterCacheViewModel<FusPresenter>>(){}.getType();
//        val type = Types(ViewModelProviders::class.java, FusPresenter::class.java)
//        val cls =  PesenterCacheViewModel::class.java as Class<PesenterCacheViewModel<FusPresenter>>

        @Suppress("UNCHECKED_CAST")
        val viewModel = ViewModelProviders.of(this).get(MVP_UID.toString(), PesenterCacheViewModel::class.java)
                as PesenterCacheViewModel<PresenterCacheBasicLazy<FusPresenter>>
        val presenterCache = PresenterCacheBasicLazy<FusPresenter>({ FusPresenter() })
        viewModel.start(presenterCache)
        return MVPDispatcher(MVP_UID,
                presenterCache,
                {layoutInflater: LayoutInflater,
                activity: Activity,
                fragmentWrapper: FragmentWrapper?,
                parentView: ViewGroup? ->
            FusVu(layoutInflater, activity, parentView = parentView)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // register fragment lifecycle distpatcher for this activity
        App.lifecycleManager.registerDispatcher(fragmentLifecycleDispatcher)
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleDispatcher, false)
    }

    override fun onDestroy() {
        super.onDestroy()

        // do after super.onDestroy() to still get the onDestroy call
        App.lifecycleManager.unregisterDispatcher(fragmentLifecycleDispatcher)
    }

}
