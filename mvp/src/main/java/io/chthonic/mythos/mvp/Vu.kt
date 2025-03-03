package io.chthonic.mythos.mvp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType

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
        inflateBinding(layoutInflater)
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

    /**
     * Inflate view binding
     * NB, don't reference binding since this method creates it.
     * Default implementation creates binding by inflating generic [VB]
     * @return VieBinding that becomes the property's [binding].
     */
    open fun inflateBinding(inflater: LayoutInflater): VB {
        val type =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VB>
        val inflateMethod: Method = if (parentView != null) {
            type.getMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java,
            )
        } else {
            type.getMethod("inflate", LayoutInflater::class.java)
        }
        return if (parentView != null) {
            inflateMethod.invoke(null, inflater, parentView, false) as VB
        } else {
            inflateMethod.invoke(null, inflater) as VB
        }
    }
}