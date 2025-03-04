package io.chthonic.mythos.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


/**
 * Created by jhavatar on 3/5/2016.
 *
 * Implement a MVP pattern using an Activity.
 * MVPDispatcher requires PresenterCache implement interface LoaderManager.LoaderCallbacks, e.g. provided PresenterCacheLoaderCallback
 * Presenter is linked from onStart() to onStop() and destroyed when supportLoaderManager calls onLoaderReset().
 * Vu is created in onCreate() and destroyed in onDestroy().
 * @param P type of Presenter.
 * @param V type of Vu.
 */
abstract class MVPActivity<P, V>: AppCompatActivity() where P : Presenter<V>, V : Vu<out ViewBinding> {
    val mvpDispatcher: MVPDispatcher<P, V> by lazy {
        createMVPDispatcher()
    }

    /**
     * @return MVPDispatcher instance used to coordinate MVP pattern.
     */
    protected abstract fun createMVPDispatcher(): MVPDispatcher<P, V>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mvpDispatcher.restorePresenterState(savedInstanceState)
        mvpDispatcher.createVu(this.layoutInflater, this)
        setContentView(checkNotNull(mvpDispatcher.vu).rootView)
    }

    override fun onResume() {
        super.onResume()
        mvpDispatcher.linkPresenter(this.intent.extras)
    }

    override fun onPause() {
        mvpDispatcher.unlinkPresenter()
        super.onPause()
    }

    override fun onDestroy() {
        mvpDispatcher.destroyVu()
        mvpDispatcher.destroyPresenterIfRequired()
        super.onDestroy()
    }


    override fun onSaveInstanceState(outState: Bundle){
        super.onSaveInstanceState(outState)
        mvpDispatcher.savePresenterState(outState)
    }
}