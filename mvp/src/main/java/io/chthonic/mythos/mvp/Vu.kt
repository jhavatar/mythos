package io.chthonic.mythos.mvp

import android.app.Activity
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by jhavatar on 3/3/2016.
 *
 * The View component of an MVP architectural pattern. Named to not confuse with all Android's "View" class
 *
 * @param layoutInflater the Inflater available in createRootView() method
 * @property activity the Activity that Vu rootView belongs to.
 * @property fragment the Fragment that Vu's rootView is a child of (Optional).
 * @property parentView the ViewGroup that is the direct parent to Vu's rootView (Optional).
 * @constructor Creates MVP's View.
 */
abstract class Vu(layoutInflater: LayoutInflater,
                  val activity: Activity,
                  val fragment: Fragment? = null,
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
     * Layout resource file that by default is inflated to be rootView of the Vu.
     * Note, If not creating rootView from a layout resource file -- override createRootView().
     * @return resource-id of a layout resource file.
     */
    abstract fun getRootViewLayoutId() : Int

    /**
     * Create rootView.
     * NB, don't reference rootView since this method creates it.
     * Default implementation creates rootView by inflating getRootViewLayoutId()'s result.
     * @return View that becomes property's rootView.
     */
    protected open fun createRootView(inflater: LayoutInflater) : View {
        return if (parentView != null) {
            inflater.inflate(getRootViewLayoutId(), parentView, false)
            
        } else {
            inflater.inflate(getRootViewLayoutId(), null)
        }
    }

}