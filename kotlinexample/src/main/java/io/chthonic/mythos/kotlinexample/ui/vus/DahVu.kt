package io.chthonic.mythos.kotlinexample.ui.vus

import android.app.Activity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import io.chthonic.mythos.kotlinexample.R
import io.chthonic.mythos.mvp.Vu
import kotlinx.android.synthetic.main.layout_dah.view.*

/**
 * Created by jhdev on 3/30/2016.
 */
class DahVu(inflater: LayoutInflater,
            activity: Activity,
            fragment: androidx.fragment.app.Fragment? = null,
            parentView: ViewGroup? = null):
        Vu(inflater,
                activity = activity,
                fragment = fragment,
                parentView = parentView) {

    override fun getRootViewLayoutId(): Int {
        return R.layout.layout_dah
    }

    fun setText(text: String) {
        rootView.dah_text.text = text
    }
}