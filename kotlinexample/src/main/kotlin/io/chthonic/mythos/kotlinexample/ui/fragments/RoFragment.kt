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
        return MVPDispatcher<RoPresenter, RoVu>(213,
                PresenterCacheLoaderCallback<RoPresenter>(this.activity, { RoPresenter() }),
                {layoutInflater: LayoutInflater, activity: Activity, fragmentWrapper: FragmentWrapper?, parentView: ViewGroup? ->
                    RoVu(layoutInflater, activity, fragmentWrapper, parentView);
                })
    }

}