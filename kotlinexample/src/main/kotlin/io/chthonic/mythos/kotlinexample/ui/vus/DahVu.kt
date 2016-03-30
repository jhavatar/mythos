package io.chthonic.mythos.kotlinexample.ui.vus

import android.app.Activity
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import io.chthonic.mythos.kotlinexample.R
import io.chthonic.mythos.mvp.VuImpl

/**
 * Created by jhdev on 3/30/2016.
 */
class DahVu(inflater: LayoutInflater,
            override val activity: Activity,
            override var fragment: Fragment? = null,
            override var parentView: ViewGroup? = null):
        VuImpl(inflater,
                activity = activity,
                fragment = fragment,
                parentView = parentView) {
    override fun getRootViewLayoutId(): Int {
        return R.layout.layout_dah;
    }
}