package io.chthonic.mythos.mvp

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Created by jhavatar on 3/9/2016.
 *
 * Coordinates the Presenter and View of a MVP architectural pattern and their callbacks.
 * @param P type of Presenter expected.
 * @param V type of View expected.
 * @property uid id that uniquely identifies dispatcher and is used i.a. as key when saving Presenter's state.
 * @property presenterCache cache that returns Presenter to use.
 * @property createVuFunction function that creates Vu to use.
 * @constructor create MVPDispatcher instance.
 */
class MVPDispatcher<P, V>(
    val uid: Int,
    val presenterCache: PresenterCache<P>,
    private val createVuFunction: (
        layoutInflater: LayoutInflater,
        activity: Activity,
        fragment: Fragment?,
        parentView: ViewGroup?
    ) -> V,
) where P : Presenter<V>, V : Vu<out ViewBinding> {

    /**
     * @param uid id that uniquely identifies dispatcher and is used i.a. as key when saving Presenter's state.
     * @param presenterCache cache that returns Presenter to use.
     * @param createVuFunction implements function that creates Vu to use.
     * @constructor create MVPDispatcher instance.
     */
    constructor(
        uid: Int,
        presenterCache: PresenterCache<P>,
        createVuFunction: CreateVuFunction<V>
    ) :
            this(uid,
                presenterCache,
                { layoutInflater: LayoutInflater,
                  activity: Activity,
                  fragment: Fragment?,
                  parentView: ViewGroup? ->
                    createVuFunction.invoke(layoutInflater, activity, fragment, parentView)
                })

    private val stateKey: String = "presenter_$uid"

    /**
     * MPV's View
     */
    var vu: V? = null
        private set

    /**
     * MPV's Presenter
     */
    val presenter: P?
        get() {
            return presenterCache.getCached()
        }

    /**
     * Last retrieved presenter state.
     */
    private var lastPresenterState: Bundle? = null

    fun restorePresenterState(inState: Bundle?) {
        if (inState?.containsKey(stateKey) == true) {
            lastPresenterState = inState.getBundle(stateKey)
        }
    }

    /**
     * Create View/Vu. Call before linking Presenter. Calls Presenter's onCreate() callback.
     * @param layoutInflater the Inflater available to inflate Vu's rootView.
     * @param activity the Activity that Vu's rootView belongs to.
     * @param fragment the Fragment that Vu's rootView is a child of (Optional).
     * @param parentView the ViewGroup that is the direct parent to Vu's rootView (Optional).
     */
    @JvmOverloads
    fun createVu(
        layoutInflater: LayoutInflater,
        activity: Activity,
        fragment: Fragment? = null,
        parentView: ViewGroup? = null
    ) {

        val nuVu = createVuFunction(
            layoutInflater,
            activity,
            fragment,
            parentView
        )
        vu = nuVu

        nuVu.onCreate()
    }

    /**
     * Link Presenter to MVP pattern and its Vu. Assumes CreateVu() has been called. Calls Presenter's onLink() callback.
     * @param args arguments passed to Presenter's onLink callback.
     */
    fun linkPresenter(vararg args: Bundle?) {

        val linkArgs = Bundle()
        args.asSequence()
            .filterNotNull()
            .forEach { linkArgs.putAll(it) }

        // presenter should exist here
        checkNotNull(presenter).onLink(checkNotNull(vu), lastPresenterState, linkArgs)
    }

    /**
     * Called to allow saving of Presenter's data when object implementing MVP pattern is about to be killed. Calls Presenter's onSaveState() callback.
     * @param outState Put Presenter's data to be saved in outState. Data will be available in Presenter's onLink() callback.
     */
    fun savePresenterState(outState: Bundle) {
        val newState = Bundle()

        // presenter should exist here
        checkNotNull(presenter).onSaveState(newState)
        if (newState.size() > 0) {
            lastPresenterState = newState
            outState.putBundle(stateKey, newState)

        } else {
            lastPresenterState = null
        }
    }

    /**
     * Unlink presenter from MVP pattern and Vu. may be linked again later. Calls Presenter's onUnlink() callback.
     */
    fun unlinkPresenter() {

        // presenter should exist here
        checkNotNull(presenter).onUnlink()
    }

    /**
     * Remove reference to Vu since will no longer be used/referenced. Calls Vu's onDestroy() callback.
     */
    fun destroyVu() {
        vu?.onDestroy()
        vu = null
    }

    fun destroyPresenterIfRequired() {
        if (presenterCache.mvpDispatcherShouldDestroy) {
            presenterCache.destroyCached()
        }
    }

    /**
     * Implements method that creates Vu.
     * @param V type of Vu that invoke method returns.
     */
    interface CreateVuFunction<out V> where  V : Vu<out ViewBinding> {

        /**
         * @param layoutInflater the Inflater available to inflate Vu's rootView.
         * @param activity the Activity that Vu's rootView belongs to.
         * @param fragment the Fragment that Vu's rootView is a child of (Optional).
         * @param parentView the ViewGroup that is the direct parent to Vu's rootView (Optional).
         * @return created Vu.
         */
        fun invoke(
            layoutInflater: LayoutInflater,
            activity: Activity,
            fragment: Fragment?,
            parentView: ViewGroup?,
        ): V
    }
}