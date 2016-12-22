package io.chthonic.mythos.kotlinexample.ui.activities

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import io.chthonic.mythos.kotlinexample.R
import io.chthonic.mythos.kotlinexample.ui.fragments.RoFragment
import io.chthonic.mythos.kotlinexample.ui.presenters.FusPresenter
import io.chthonic.mythos.kotlinexample.ui.vus.FusVu
import io.chthonic.mythos.mvp.*

class FusActivity : MVPActivity<FusPresenter, FusVu>() {

    private var fragment: Fragment? = null;

    override fun createMVPDispatcher(): MVPDispatcher<FusPresenter, FusVu> {
        Log.d("mew", "FusActivity.createMVPDispatcher: this = " + this + ", func = " + {FusPresenter()});
        val disp:  MVPDispatcher<FusPresenter, FusVu> = object : MVPDispatcher<FusPresenter, FusVu>(PresenterCacheLoaderCallback<FusPresenter>(this, {FusPresenter()})) {
            override val uid = 1234

            override fun createVu(inflater: LayoutInflater, activity: Activity, fragment: FragmentWrapper?, parentView: ViewGroup?): FusVu {
                Log.d("mew", "FusActivity.createVu");
                return FusVu(inflater, activity = activity, parentView = parentView);
            }

        };

        Log.d("mew", "FusActivity.createMVPDispatcher: disp = " + disp);
        return disp
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("mew", "FusActivity.onCreate");
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState);
        }
        if (fragment == null) {

            var tempFragment: Fragment? = supportFragmentManager.findFragmentByTag(RoFragment.Companion.TAG);
            if (tempFragment != null) {
                supportFragmentManager.beginTransaction().remove(tempFragment).commit();
            }

            fragment = RoFragment();
            supportFragmentManager.beginTransaction()
                    .add(R.id.child_container, fragment, RoFragment.Companion.TAG)
                    .commit();
        }
    }


    override fun onSaveInstanceState (outState: Bundle) {
        super.onSaveInstanceState(outState);
        if (fragment != null) {
            supportFragmentManager.putFragment(outState, RoFragment.Companion.TAG, fragment);
        }
    }

    fun restoreInstanceState(inState: Bundle?){
        if (inState != null) {
            if (inState.containsKey(RoFragment.Companion.TAG)) {
                fragment = supportFragmentManager.getFragment(inState, RoFragment.Companion.TAG);
            }
        }
    }
}
