package io.chthonic.mythos.kotlinexample.ui.vus

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import io.chthonic.mythos.kotlinexample.R
import io.chthonic.mythos.mvp.FragmentWrapper
import io.chthonic.mythos.mvp.Vu

/**
 * Created by jhdev on 3/30/2016.
 */
class DahVu(inflater: LayoutInflater,
            activity: Activity,
            fragmentWrapper: FragmentWrapper? = null,
            parentView: ViewGroup? = null):
        Vu(inflater,
                activity = activity,
                fragmentWrapper = fragmentWrapper,
                parentView = parentView) {
    override fun getRootViewLayoutId(): Int {
        return R.layout.layout_dah
    }
}