package io.chthonic.mythos.mvp

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import java.lang.ref.WeakReference

/**
 * Created by jhavatar on 3/3/2016.
 *
 * The Presenter component of an MVP architectural pattern.
 * @param V type of Vu that is gets linked to Presenter.
 */
abstract class Presenter<V> where V : Vu<out ViewBinding> {

    /** Memory-leak-safe reference to attached Vu */
    private var _vuRef: WeakReference<V>? = null

    val vu: V?
        get() {
            return _vuRef?.get()
        }

    /**
     * @return true if a Vu is linked.
     */
    fun isLinked(): Boolean {
        return (this.vu != null)
    }

    /**
     * True if it is the first time this Presenter is linked.
     */
    var firstLink: Boolean = true
        private set


    /**
     * keep track of the hashCode of the last Vu linked to calculate firstLinkWithVu
     */
    private var lastVuHash: Int? = null

    /**
     * True if it is the first time this Presenter is linked with its current Vu
     */
    var firstLinkWithVu: Boolean = true
        private set

    /**
     * Called when Presenter is linked to object implementing MVP pattern and ([vu]).
     * @param [vu] View in MVP.
     * @param inState possible prevous presenter state that was saved.
     * @param args arguments passed to object implementing MVP.
     */
    open fun onLink(vu: V, inState: Bundle?, args: Bundle) {
        this._vuRef = WeakReference(vu)
        val nuVuHash = vu.hashCode()
        firstLinkWithVu = nuVuHash != lastVuHash
        lastVuHash = nuVuHash
    }

    /**
     * Called when Presenter is unlinked from MVP pattern, i.e. object implementing pattern and Vu.
     * Can possible be linked again later.
     */
    open fun onUnlink() {
        this._vuRef = null
        firstLink = false
        firstLinkWithVu = false
    }

    /**
     * Called when presenter object is destroyed, i.e. it will no longer be referenced or linked.
     * Perform final Presenter cleanup.
     */
    open fun onDestroy() {
    }

    /**
     * Called to allow saving of Presenter's data when object implementing MVP pattern is about to be killed.
     *
     * @param outState put Presenter's data to be saved in outState. Data will be available again in onLink() call.
     */
    open fun onSaveState(outState: Bundle) {

    }
}