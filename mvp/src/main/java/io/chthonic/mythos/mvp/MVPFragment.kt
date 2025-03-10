package io.chthonic.mythos.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Created by jhavatar on 3/4/2016.
 *
 * Implement a MVP pattern using a support Fragment.
 * MVPDispatcher requires PresenterCache implement interface LoaderManager.LoaderCallbacks, e.g. provided PresenterCacheLoaderCallback
 * Presenter is linked from onResume() to onPause() and destroyed when loaderManager calls onLoaderReset().
 * Vu is created in onCreateView() and destroyed in onDestroyView().
 * @param P type of Presenter.
 * @param V type of Vu.
 */
abstract class MVPFragment<P, V> : Fragment() where P : Presenter<V>, V : Vu<out ViewBinding> {

    val mvpDispatcher: MVPDispatcher<P, V> by lazy {
        createMVPDispatcher()
    }

    /**
     * @return MVPDispatcher instance used to coordinate MVP pattern.
     */
    abstract fun createMVPDispatcher(): MVPDispatcher<P, V>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        mvpDispatcher.restorePresenterState(savedInstanceState)
        mvpDispatcher.createVu(
            inflater,
            activity = checkNotNull(this.activity),
            fragment = this,
            parentView = container,
        )
        return checkNotNull(mvpDispatcher.vu).rootView
    }

    override fun onResume() {
        super.onResume()
        mvpDispatcher.linkPresenter(this.activity?.intent?.extras, this.arguments)
    }

    override fun onPause() {
        mvpDispatcher.unlinkPresenter()
        super.onPause()
    }

    override fun onDestroyView() {
        mvpDispatcher.destroyVu()
        super.onDestroyView()
    }

    override fun onDestroy() {
        mvpDispatcher.destroyPresenterIfRequired()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mvpDispatcher.savePresenterState(outState)
    }
}