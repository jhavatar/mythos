package io.chthonic.mythos.kotlinexample.ui.vus

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.chthonic.mythos.kotlinexample.R
import io.chthonic.mythos.kotlinexample.databinding.FragmentRoBinding
import io.chthonic.mythos.kotlinexample.ui.layouts.DahLayout
import io.chthonic.mythos.mvp.Vu

/**
 * Created by jhavatar on 3/22/2016.
 */
class RoVu(
    layoutInflater: LayoutInflater,
    activity: Activity,
    fragment: Fragment? = null,
    parentView: ViewGroup? = null,
) : Vu<FragmentRoBinding>(
    layoutInflater,
    activity = activity,
    fragment = fragment,
    parentView = parentView,
) {

    var toggleDahListener: (() -> Unit)? = null

    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        parentView: ViewGroup?,
    ): FragmentRoBinding =
        if (parentView != null) {
            FragmentRoBinding.inflate(layoutInflater, parentView, false)
        } else {
            FragmentRoBinding.inflate(layoutInflater)
        }

    override fun onCreate() {
        super.onCreate()

        binding.buttonToggleDah.setOnClickListener { _ ->
            toggleDahListener?.invoke()
        }
    }

    fun updateDahDisplay(show: Boolean) {
        val dah = rootView.findViewById<View>(R.id.dah_layout)

        if (show && (dah == null)) {
            val roLayout =
                DahLayout(activity, activity.resources.getString(R.string.ro_lifecycle_key))
            roLayout.id = R.id.dah_layout
            (rootView as ViewGroup).addView(roLayout)

        } else if (!show && (dah != null)) {
            (rootView as ViewGroup).removeView(dah)
        }
    }

    fun setText(text: String) {
        binding.roText.text = text
    }
}