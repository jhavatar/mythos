package io.chthonic.mythos.mvp

import android.app.Activity
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by jhavatar on 3/5/2016.
 */
abstract class VuImpl(inflater: LayoutInflater,
                      override val activity: Activity,
                      override var fragment: Fragment? = null,
                      override var parentView: ViewGroup? = null) : Vu {

    override var focused = false;
    override var attached = true;

    override val rootView: View by lazy {
        createRootView(inflater);
    }

    abstract fun getRootViewLayoutId() : Int;

    fun createRootView(inflater: LayoutInflater) : View {
        if (parentView != null) {
            return inflater.inflate(getRootViewLayoutId(), parentView, false);
        } else {
            return inflater.inflate(getRootViewLayoutId(), null);
        }
    }
}