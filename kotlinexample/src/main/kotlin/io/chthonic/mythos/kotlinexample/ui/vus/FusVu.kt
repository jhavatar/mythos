package io.chthonic.mythos.kotlinexample.ui.vus

import android.app.Activity
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import io.chthonic.mythos.kotlinexample.R
import io.chthonic.mythos.mvp.Vu

/**
 * Created by jhavatar on 3/3/2016.
 */
class FusVu(inflater: LayoutInflater,
            activity: Activity,
            fragment: Fragment? = null,
            parentView: ViewGroup? = null) :
        Vu(inflater,
                activity = activity,
                fragment = fragment,
                parentView = parentView) {

    override fun getRootViewLayoutId(): Int {
        return R.layout.activity_fus;
    }
}