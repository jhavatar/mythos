package io.cthonic.mythos.kotlinexample.ui.fragments

import android.app.Activity
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import io.cthonic.mythos.kotlinexample.ui.presenters.RoPresenter
import io.cthonic.mythos.kotlinexample.ui.vus.RoVu
import io.cthonic.mythos.mvp.MVPDispatcher
import io.cthonic.mythos.mvp.MVPFragment

/**
 * Created by jhavatar on 3/22/2016.
 */
class RoFragment(): MVPFragment<RoPresenter, RoVu>() {

    companion object {
        val TAG = "RoFragment";
    }

    override fun createMVPDispatcher(): MVPDispatcher<RoPresenter, RoVu> {
        return object : MVPDispatcher<RoPresenter, RoVu>() {

            override fun createPresenter(): RoPresenter {
                return RoPresenter();
            }

            override fun createVu(inflater: LayoutInflater, activity: Activity, fragment: Fragment?, parentView: ViewGroup?): RoVu {
                return RoVu(inflater, activity, fragment, parentView);
            }
        };
    }

}