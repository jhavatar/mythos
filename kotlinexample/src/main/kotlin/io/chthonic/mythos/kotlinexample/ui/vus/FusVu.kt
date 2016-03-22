package io.chthonic.mythos.kotlinexample.ui.vus

import android.app.Activity
import android.graphics.Color
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import io.chthonic.mythos.kotlinexample.R
import io.chthonic.mythos.mvp.VuImpl
import kotlinx.android.synthetic.main.layout_fus.view.*

/**
 * Created by jhavatar on 3/3/2016.
 */
class FusVu(inflater: LayoutInflater,
            override val activity: Activity,
            override var fragment: Fragment? = null,
            override var parentView: ViewGroup? = null) :
        VuImpl(inflater,
                activity = activity,
                fragment = fragment,
                parentView = parentView) {

    override fun getRootViewLayoutId(): Int {
       return R.layout.layout_fus;
    }

    fun setTitle(text: String) {
        this.rootView.text.text = text;
    }

    fun setBg(color: String) {
        this.rootView.setBackgroundColor(Color.parseColor(color));
    }
}