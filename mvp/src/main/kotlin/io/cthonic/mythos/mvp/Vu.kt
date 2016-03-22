package io.cthonic.mythos.mvp

import android.app.Activity
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup

/**
 * Created by jhavatar on 3/3/2016.
 */
interface Vu {

    val activity: Activity;
    var fragment: Fragment?;
    var parentView: ViewGroup?;
    val rootView: View;

    var attached: Boolean;
    var focused: Boolean;

    fun onFocusedChanged(hasFocus: Boolean) {
        focused = true;
    }

    fun onDestroy() {
        attached = false;
    }

}