package io.chthonic.mythos.kotlinexample.ui.fragments

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import io.chthonic.mythos.kotlinexample.ui.presenters.FusPresenter
import io.chthonic.mythos.kotlinexample.ui.presenters.RoPresenter
import io.chthonic.mythos.kotlinexample.ui.vus.RoVu
import io.chthonic.mythos.mvp.FragmentWrapper
import io.chthonic.mythos.mvp.MVPDispatcher
import io.chthonic.mythos.mvp.MVPFragment
import io.chthonic.mythos.mvp.PresenterCacheLoaderCallback

/**
 * Created by jhavatar on 3/22/2016.
 */
class RoFragment(): MVPFragment<RoPresenter, RoVu>() {

    companion object {
        val TAG = "RoFragment";
    }

    override fun createMVPDispatcher(): MVPDispatcher<RoPresenter, RoVu> {
        return object : MVPDispatcher<RoPresenter, RoVu>(PresenterCacheLoaderCallback<RoPresenter>(this.activity, { RoPresenter() })) {
            override val uid = 213

            override fun createVu(inflater: LayoutInflater, activity: Activity, fragment: FragmentWrapper?, parentView: ViewGroup?): RoVu {
                return RoVu(inflater, activity, fragment, parentView);
            }
        };
    }

}