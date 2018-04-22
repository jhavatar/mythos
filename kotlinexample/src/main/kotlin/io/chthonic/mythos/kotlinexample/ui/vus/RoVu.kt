package io.chthonic.mythos.kotlinexample.ui.vus

import android.app.Activity
import android.util.Log
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

    var toggleDahListener: (() -> Unit)? = null

    override fun onCreate() {
        super.onCreate()

        rootView.button_toggle_dah.setOnClickListener { v ->
            toggleDahListener?.invoke()
        }
    }

    override fun getRootViewLayoutId(): Int {
        return R.layout.fragment_ro
    }

    fun updateDahDisplay(show: Boolean) {
        val dah = rootView.dah_layout
        Log.d("RoVu", "updateDahDisplay: show = $show, dah_layout = ${dah}")

        if (show && (dah == null)) {
            Log.d("RoVu", "updateDahDisplay: add dah")
            val roLayout = DahLayout(activity, activity.resources.getString(R.string.ro_lifecycle_key))
            roLayout.id = R.id.dah_layout
            (rootView as ViewGroup).addView(roLayout)
            Log.d("RoVu", "updateDahDisplay: after add dah")

        } else if (!show && (dah != null)) {
            Log.d("RoVu", "updateDahDisplay: remove dah")
            (rootView as ViewGroup).removeView(dah)
            Log.d("RoVu", "updateDahDisplay: after remove dah")

        } else {
            Log.d("RoVu", "updateDahDisplay: do nothing")
        }
    }

}