package io.chthonic.mythos.kotlinexample.ui.activities

import android.app.Activity
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

    private var fragment: Fragment? = null
    private val fragmentLifecycleDispatcher: SupportFragmentLifecycleDispatcher by lazy {
        SupportFragmentLifecycleDispatcher(mapOf(Pair(RoFragment::class.java, RoFragment.lIFECYCLE_KEY)))
    }

    override fun createMVPDispatcher(): MVPDispatcher<FusPresenter, FusVu> {
        return MVPDispatcher(MVP_UID,
                PresenterCacheLoaderCallback(this, {FusPresenter()}),
                {layoutInflater: LayoutInflater,
                activity: Activity,
                fragmentWrapper: FragmentWrapper?,
                parentView: ViewGroup? ->
            FusVu(layoutInflater, activity, parentView = parentView)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState)
        }

        App.lifecycleManager.registerDispatcher(fragmentLifecycleDispatcher)
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleDispatcher, false)

        if (fragment == null) {

            val tempFragment: Fragment? = supportFragmentManager.findFragmentByTag(RoFragment.TAG)
            if (tempFragment != null) {
                supportFragmentManager.beginTransaction().remove(tempFragment).commit()
            }

            fragment = RoFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.child_container, fragment, RoFragment.TAG)
                    .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // do after super.onDestory() to still get the onDestroy call
        App.lifecycleManager.unregisterDispatcher(fragmentLifecycleDispatcher)
    }

    override fun onSaveInstanceState (outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (fragment != null) {
            supportFragmentManager.putFragment(outState, RoFragment.TAG, fragment)
        }
    }

    fun restoreInstanceState(inState: Bundle?){
        if (inState != null) {
            if (inState.containsKey(RoFragment.TAG)) {
                fragment = supportFragmentManager.getFragment(inState, RoFragment.TAG)
            }
        }
    }
}
