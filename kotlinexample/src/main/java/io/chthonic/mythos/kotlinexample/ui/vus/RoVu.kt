package io.chthonic.mythos.kotlinexample.ui.vus

import android.app.Activity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import io.chthonic.mythos.kotlinexample.R
import io.chthonic.mythos.kotlinexample.ui.layouts.DahLayout
import io.chthonic.mythos.mvp.Vu
import kotlinx.android.synthetic.main.fragment_ro.view.*

/**
 * Created by jhavatar on 3/22/2016.
 */
class RoVu (inflater: LayoutInflater,
            activity: Activity,
            fragment: androidx.fragment.app.Fragment? = null,
            parentView: ViewGroup? = null) :
        Vu(inflater,
                activity = activity,
                fragment = fragment,
                parentView = parentView) {

    var toggleDahListener: (() -> Unit)? = null

    override fun onCreate() {
        super.onCreate()

        rootView.button_toggle_dah.setOnClickListener { _ ->
            toggleDahListener?.invoke()
        }
    }

    override fun getRootViewLayoutId(): Int {
        return R.layout.fragment_ro
    }

    fun updateDahDisplay(show: Boolean) {
        val dah = rootView.dah_layout

        if (show && (dah == null)) {
            val roLayout = DahLayout(activity, activity.resources.getString(R.string.ro_lifecycle_key))
            roLayout.id = R.id.dah_layout
            (rootView as ViewGroup).addView(roLayout)

        } else if (!show && (dah != null)) {
            (rootView as ViewGroup).removeView(dah)
        }
    }

    fun setText(text: String) {
        rootView.ro_text.text = text
    }
}