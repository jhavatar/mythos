package io.chthonic.mythos.mvp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

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
abstract class Vu<VB : ViewBinding>(
    layoutInflater: LayoutInflater,
    val activity: Activity,
    val fragment: Fragment? = null,
    val parentView: ViewGroup? = null,
) {

    /**
     * The view binding of the views that the Vu manages.
     */
    val binding: VB by lazy {
        inflateBinding(layoutInflater, parentView)
    }

    /**
     * The root of the views that the Vu manages.
     */
    val rootView: View
        get() = binding.root

    /**
     * True if Vu has been destroyed, i.e. rootView will no longer be referenced.
     * */
    var destroyed: Boolean = false
        private set

    /**
     * Inflate view binding
     * NB, don't reference [binding] since this method creates it.
     * @return VieBinding that becomes property [binding].
     */
    protected abstract fun inflateBinding(
        layoutInflater: LayoutInflater,
        parentView: ViewGroup?
    ): VB

    /**
     * Called after binding is created.
     */
    open fun onCreate() {

    }

    /**
     * Called when Vu is no longer needed and rootView will no longer be referenced. Perform any final Vu cleanup.
     */
    open fun onDestroy() {
        destroyed = true
    }
}