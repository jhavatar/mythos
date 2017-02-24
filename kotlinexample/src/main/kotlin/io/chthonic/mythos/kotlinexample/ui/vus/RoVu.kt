package io.chthonic.mythos.kotlinexample.ui.vus

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import io.chthonic.mythos.kotlinexample.R
import io.chthonic.mythos.kotlinexample.ui.layouts.DahLayout
import io.chthonic.mythos.mvp.FragmentWrapper
import io.chthonic.mythos.mvp.Vu
import kotlinx.android.synthetic.main.fragment_ro.view.*

/**
 * Created by jhavatar on 3/22/2016.
 */
class RoVu (inflater: LayoutInflater,
            activity: Activity,
            fragmentWrapper: FragmentWrapper? = null,
            parentView: ViewGroup? = null) :
        Vu(inflater,
                activity = activity,
                fragmentWrapper = fragmentWrapper,
                parentView = parentView) {

    override fun onCreate() {
        super.onCreate()

        rootView.button_toggle_dah.setOnClickListener { v ->
            if (rootView.dah_layout != null) {
                (rootView as ViewGroup).removeView(rootView.dah_layout)

            } else {
                val roLayout = DahLayout(activity, null)
                roLayout.id = R.id.dah_layout
                (rootView as ViewGroup).addView(roLayout)
            }
        }
    }

    override fun getRootViewLayoutId(): Int {
        return R.layout.fragment_ro
    }


}