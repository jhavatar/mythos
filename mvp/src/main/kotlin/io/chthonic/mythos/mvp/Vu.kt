package io.chthonic.mythos.mvp

import android.app.Activity
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by jhavatar on 3/3/2016.
 */
abstract class Vu(inflater: LayoutInflater,
                  val activity: Activity,
                  var fragment: Fragment? = null,
                  var parentView: ViewGroup? = null) {

    val rootView : View by lazy {
        createRootView(inflater);
    }

    var attached: Boolean = true;
    var focused: Boolean = false;

    fun onFocusedChanged(hasFocus: Boolean) {
        focused = true;
    }

    open fun onDestroy() {
        attached = false;
    }

    abstract fun getRootViewLayoutId() : Int;

    protected fun createRootView(inflater: LayoutInflater) : View {
        if (parentView != null) {
            return inflater.inflate(getRootViewLayoutId(), parentView, false);
        } else {
            return inflater.inflate(getRootViewLayoutId(), null);
        }
    }

}