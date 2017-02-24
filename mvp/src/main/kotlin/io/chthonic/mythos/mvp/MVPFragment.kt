package io.chthonic.mythos.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.LoaderManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by jhavatar on 3/4/2016.
 *
 * Implement a MVP pattern using a Fragment.
 */
abstract class MVPFragment<P, V> : Fragment() where P : Presenter<V>, V : Vu {

    val mvpDispatcher: MVPDispatcher<P, V> by lazy {
        createMVPDispatcher()
    }

    abstract fun createMVPDispatcher(): MVPDispatcher<P, V>

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {

        mvpDispatcher.restorePresenterState(savedInstanceState)
        mvpDispatcher.createVu(inflater,
                activity = this.activity,
                fragment = FragmentWrapper(this),
                parentView = container)
        return mvpDispatcher.vu!!.rootView
    }

    override fun onActivityCreated (savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loaderManager.initLoader(mvpDispatcher.uid,
                null,
                mvpDispatcher.presenterCache as LoaderManager.LoaderCallbacks<P>)
    }

    override fun onResume() {
        super.onResume()
        mvpDispatcher.linkPresenter(this.activity.intent.extras, this.arguments)
    }

    override fun onPause() {
        mvpDispatcher.unlinkPresenter()
        super.onPause()
    }

    override fun onDestroyView() {
        mvpDispatcher.destroyVu()
        super.onDestroyView()
    }

    override fun onSaveInstanceState (outState: Bundle) {
        super.onSaveInstanceState(outState)
        mvpDispatcher.savePresenterState(outState)
    }

}