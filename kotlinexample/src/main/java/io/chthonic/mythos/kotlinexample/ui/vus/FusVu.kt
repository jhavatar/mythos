package io.chthonic.mythos.kotlinexample.ui.vus

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.chthonic.mythos.kotlinexample.R
import io.chthonic.mythos.kotlinexample.databinding.ActivityFusBinding
import io.chthonic.mythos.kotlinexample.ui.fragments.RoFragment
import io.chthonic.mythos.kotlinexample.ui.layouts.DahLayout
import io.chthonic.mythos.mvp.Vu

/**
 * Created by jhavatar on 3/3/2016.
 */
class FusVu(
    layoutInflater: LayoutInflater,
    activity: Activity,
    fragment: Fragment? = null,
    parentView: ViewGroup? = null,
) : Vu<ActivityFusBinding>(
    layoutInflater,
    activity = activity,
    fragment = fragment,
    parentView = parentView,
) {

    var toggleRoListener: (() -> Unit)? = null
    var toggleDahListener: (() -> Unit)? = null

    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        parentView: ViewGroup?,
    ): ActivityFusBinding =
        if (parentView != null) {
            ActivityFusBinding.inflate(layoutInflater, parentView, false)
        } else {
            ActivityFusBinding.inflate(layoutInflater)
        }

    override fun onCreate() {
        super.onCreate()

        binding.buttonToggleRo.setOnClickListener { _ ->
            toggleRoListener?.invoke()
        }

        binding.buttonToggleDah.setOnClickListener { _ ->
            toggleDahListener?.invoke()
        }
    }

    fun updateRoDisplay(show: Boolean) {
        val supportFragmentManager = (activity as AppCompatActivity).supportFragmentManager
        val rohFragment: Fragment? = supportFragmentManager.findFragmentByTag(RoFragment.TAG)

        if (show and (rohFragment == null)) {
            val fragment = RoFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, RoFragment.TAG)
                .commit()

        } else if (!show && (rohFragment != null)) {
            supportFragmentManager.beginTransaction().remove(rohFragment).commit()
        }
    }

    fun updateDahDisplay(show: Boolean) {
        val dah = binding.childContainer.findViewById<DahLayout>(R.id.dah_layout)

        if (show && (dah == null)) {
            val roLayout =
                DahLayout(activity, activity.resources.getString(R.string.fus_lifecycle_key))
            roLayout.id = R.id.dah_layout
            binding.childContainer.addView(roLayout)

        } else if (!show && (dah != null)) {
            (dah.parent as ViewGroup).removeView(dah)
            rootView.invalidate()
        }
    }

    fun setText(text: String) {
        binding.fusText.text = text
    }

}