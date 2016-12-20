package io.chthonic.mythos.mvp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by jhavatar on 3/3/2016.
 *
 * Manages the View component of an MVP architectural pattern.
 *
 * @param activity the Activity that Vu rootView belongs to.
 * @param fragment the Fragment that Vu's rootView is a child of (Optional).
 * @param parentView the ViewGroup that is the direct parent to Vu's rootView (Optional).
 * @constructor Initialize MVP's View. Call when view is attached before it is displayed
 */
abstract class Vu(inflater: LayoutInflater,
                  val activity: Activity,
                  var fragment: FragmentWrapper? = null,
                  var parentView: ViewGroup? = null) {

    /**
     * The root of the views that the Vu manages. Can be lazily created as soon as Vu is created.
     */
    val rootView : View by lazy {
        createRootView(inflater);
    }

    /** True if Vu is attached to a Presenter */
    var attached: Boolean = true
        private set

//    /** True if Vu is in screen's foreground */
//    var focused: Boolean = false;

//    /**
//     * Called when Vu is moves to and from the screen's foreground.
//     */
//    fun onFocusedChanged(hasFocus: Boolean) {
//        focused = true;
//    }

    /**
     * Called after Vu is detached from Presenter its rootView will no longer be used. Perform any final Vu cleanup.
     */
    open fun onDetached() {
        attached = false;
    }

    /**
     * Return id of layout resource file that is the rootView of the Vu.
     */
    abstract fun getRootViewLayoutId() : Int;

    /**
     * Create rootView by inflating getRootViewLayoutId()'s layout.
     */
    protected fun createRootView(inflater: LayoutInflater) : View {
        if (parentView != null) {
            return inflater.inflate(getRootViewLayoutId(), parentView, false);
            
        } else {
            return inflater.inflate(getRootViewLayoutId(), null);
        }
    }

}