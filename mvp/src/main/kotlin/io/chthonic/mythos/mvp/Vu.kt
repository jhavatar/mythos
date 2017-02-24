package io.chthonic.mythos.mvp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by jhavatar on 3/3/2016.
 *
 * The View component of an MVP architectural pattern.
 *
 * @param activity the Activity that Vu rootView belongs to.
 * @param fragmentWrapper Wraps the Fragment that Vu's rootView is a child of (Optional).
 * @param parentView the ViewGroup that is the direct parent to Vu's rootView (Optional).
 * @constructor Initialize MVP's View. Call when view is attached before it is displayed
 */

abstract class Vu(layoutInflater: LayoutInflater,
                  val activity: Activity,
                  val fragmentWrapper: FragmentWrapper? = null,
                  val parentView: ViewGroup? = null) {

    /**
     * The root of the views that the Vu manages.
     */
    val rootView : View by lazy {
        createRootView(layoutInflater)
    }

    /**
     * True if Vu has been destroyed, i.e. rootView will no longer be referenced.
     * */
    var destroyed: Boolean = false
        private set


    /**
     * Called after rootView is created.
     */
    open fun onCreate() {

    }

    /**
     * Called when Vu is no longer needed and rootView will no longer be referenced. Perform any final Vu cleanup.
     */
    open fun onDestroy() {
        destroyed = true
    }

    /**
     * Return id of layout resource file that by default is inflated to be rootView of the Vu.
     * Note, override createRootView() if not creating rootView from getRootViewLayoutId() result.
     */
    abstract fun getRootViewLayoutId() : Int

    /**
     * Create rootView.
     * NB, don't reference rootView since this method creates it.
     * Default implementation creates rootView by inflating getRootViewLayoutId()'s result.
     */
    open protected fun createRootView(inflater: LayoutInflater) : View {
        if (parentView != null) {
            return inflater.inflate(getRootViewLayoutId(), parentView, false)
            
        } else {
            return inflater.inflate(getRootViewLayoutId(), null)
        }
    }

}