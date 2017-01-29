package io.chthonic.mythos.mvp

import android.os.Bundle
import java.lang.ref.WeakReference

/**
 * Created by jhavatar on 3/3/2016.
 *
 * The Presenter component of an MVP architectural pattern.
 */
abstract class Presenter<V> where V : Vu {

    /** Memory-leak-safe reference to attached Vu */
    private var _vuRef: WeakReference<V>? = null

    var firstLink: Boolean = true
        private set

    /**
     * Return true if a Vu is linked.
     */
    fun isLinked(): Boolean {
        return (_vuRef != null) && (getVu() != null)
    }

    /**
     * Return linked Vu.
     */
    fun getVu(): V? {
        return if (isLinked()) _vuRef?.get() else  null
    }

    open fun onLinked(vu: V, inState: Bundle?, args: Bundle) {
        this._vuRef = WeakReference<V>(vu)
    }

    open fun onUnlinked() {
        this._vuRef = null
        firstLink = false
    }

    /**
     * Called when presenter object is being destroyed. Perform final Presenter cleanup.
     */
    open fun onDestroy() {
    }

    /**
     * Called to allow saving of Presenter's data when presented object is about to be killed.
     *
     * @param outState Put Presenter's data to be saved in outState. Data will be available in initialize() call.
     */
    open fun onSaveState(outState: Bundle) {

    }
}