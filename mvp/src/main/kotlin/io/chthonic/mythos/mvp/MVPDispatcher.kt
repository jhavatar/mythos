package io.chthonic.mythos.mvp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by jhavatar on 3/9/2016.
 *
 * Coordinates the Presenter and View of a MVP architectural pattern and their lifecycle callbacks.
 */
class MVPDispatcher<P, V> (val uid: Int,
                           val presenterCache: PresenterCache<P>,
                           val createVu: (inflater: LayoutInflater,
                                          activity: Activity,
                                          fragmentWrapper: FragmentWrapper?,
                                          parentView: ViewGroup?) -> V) where P : Presenter<V>,  V : Vu {

    private val stateKey: String = "presenter_" + uid

    /** Reference to Vu instance */
    var vu: V? = null
        private set

    val presenter: P?
        get() {
            return presenterCache.get()
        }

    private var lastPresenterState: Bundle? = null

    fun restorePresenterState(inState: Bundle?) {
        if (inState?.containsKey(stateKey) ?: false) {
            lastPresenterState = inState?.get(stateKey) as Bundle
        }
    }


    @JvmOverloads
    fun attachVu(inflater: LayoutInflater,
               activity: Activity,
               parentView: ViewGroup? = null,
               fragment: FragmentWrapper? = null) {

        vu = createVu(inflater,
                activity,
                fragment,
                parentView)
        Log.d("mew", "attachVu, vu = " + vu)
    }


    fun linkPresenter(vararg args: Bundle?) {

        val linkArgs = Bundle()
        args.asSequence()
                .filterNotNull()
                .forEach { linkArgs.putAll(it) }

        Log.d("mew", "linkPresenter: presenter = $presenter, vu = $vu, lastPresenterState = $lastPresenterState, linkArgs = $linkArgs")
        presenter!!.onLinked(vu!!, lastPresenterState, linkArgs)
    }

    /**
     * Called to allow saving of Presenter's data when presented object is about to be killed.
     *
     * @param outState Put Presenter's data to be saved in outState. Data will be available in initialize() call.
     */
    fun savePresenterState(outState: Bundle) {
        val newState = Bundle()
        presenter!!.onSaveState(newState)
        if (newState.size() > 0) {
            lastPresenterState = newState
            outState.putBundle(stateKey, newState)

        } else {
            lastPresenterState = null
        }
    }


    fun unlinkPresenter() {
        presenter!!.onUnlinked()
    }

    fun detachVu() {
        vu!!.onDetached()
        vu = null
    }
}