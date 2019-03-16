package io.chthonic.mythos.kotlinexample.ui.vus

//import kotlinx.android.synthetic.main.activity_fus
import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import io.chthonic.mythos.kotlinexample.R
import io.chthonic.mythos.kotlinexample.ui.fragments.RoFragment
import io.chthonic.mythos.kotlinexample.ui.layouts.DahLayout
import io.chthonic.mythos.mvp.Vu
import kotlinx.android.synthetic.main.activity_fus.view.*

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

    var toggleRoListener: (() -> Unit)? = null
    var toggleDahListener: (() -> Unit)? = null


    override fun getRootViewLayoutId(): Int {
        return R.layout.activity_fus
    }

    override fun onCreate() {
        super.onCreate()

        rootView.button_toggle_ro.setOnClickListener { _ ->
            toggleRoListener?.invoke()
        }

        rootView.button_toggle_dah.setOnClickListener { _ ->
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
        val dah = rootView.child_container.dah_layout

        if (show && (dah == null)) {
            val roLayout = DahLayout(activity, activity.resources.getString(R.string.fus_lifecycle_key))
            roLayout.id = R.id.dah_layout
            rootView.child_container.addView(roLayout)

        } else if (!show && (dah != null)) {
            (dah.parent as ViewGroup).removeView(dah)
            rootView.invalidate()
        }
    }

    fun setText(text: String) {
        rootView.fus_text.text = text
    }

}