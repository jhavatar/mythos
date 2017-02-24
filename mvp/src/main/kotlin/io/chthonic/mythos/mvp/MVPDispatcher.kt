package io.chthonic.mythos.mvp

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by jhavatar on 3/9/2016.
 *
 * Coordinates the Presenter and View of a MVP architectural pattern and their callbacks.
 */
class MVPDispatcher<P, V> (val uid: Int,
                           val presenterCache: PresenterCache<P>,
                           private val createVuFun: (inflater: LayoutInflater,
                                          activity: Activity,
                                          fragmentWrapper: FragmentWrapper?,
                                          parentView: ViewGroup?) -> V) where P : Presenter<V>,  V : Vu {

    companion object {
        private val uniqueId = AtomicInteger()

        @JvmOverloads
        fun genUniquePresenterId(exclude: List<Int> = emptyList()): Int {
            var newId: Int = uniqueId.incrementAndGet();
            while (exclude.contains(newId)) {
                newId = uniqueId.incrementAndGet();
            }

            return newId;
        }
    }

    constructor(uid: Int,
                presenterCache: PresenterCache<P>,
                createVuFun: CreateVuFunction<V>) :
                    this(uid,
                            presenterCache,
                            {inflater: LayoutInflater,
                             activity: Activity,
                             fragmentWrapper: FragmentWrapper?,
                             parentView: ViewGroup? ->
                                createVuFun.invoke(inflater, activity, fragmentWrapper, parentView)
                    })

    private val stateKey: String = "presenter_" + uid

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
    fun createVu(inflater: LayoutInflater,
                 activity: Activity,
                 parentView: ViewGroup? = null,
                 fragment: FragmentWrapper? = null) {

        vu = createVuFun(inflater,
                activity,
                fragment,
                parentView)
    }


    fun linkPresenter(vararg args: Bundle?) {

        val linkArgs = Bundle()
        args.asSequence()
                .filterNotNull()
                .forEach { linkArgs.putAll(it) }

        presenter!!.onLink(vu!!, lastPresenterState, linkArgs)
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
        presenter!!.onUnlink()
    }

    fun destroyVu() {
        vu!!.onDestroy()
        vu = null
    }

    interface CreateVuFunction<out V> where  V : Vu {
        fun invoke(inflater: LayoutInflater,
                   activity: Activity,
                   fragmentWrapper: FragmentWrapper?,
                   parentView: ViewGroup?) : V
    }
}