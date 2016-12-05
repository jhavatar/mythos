package io.chthonic.mythos.mvp

import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader

/**
 * Created by jhavatar on 12/4/2016.
 */
interface PresenterLoaderCallbacks<P, V> : LoaderManager.LoaderCallbacks<P> where P : Presenter<V>,  V : Vu {

    var vu: V?
    var presenter: P?


    /**
     * Called when presented object is being destroyed. Lifecycle callback.
     */
    fun destroyPresenter() {
        presenter!!.onDestroy();
    }

    fun createPresenter(): P;


    override fun onCreateLoader(id: Int, arg: Bundle): Loader<P> {
        return PresenterLoader<P>(context, {
            createPresenter()
        }) as (Loader<P>)
    }

    override fun onLoadFinished(loader: Loader<P>, presenter: P) {
        this.presenter = presenter
    }

    override fun onLoaderReset(loader: Loader<P>) {
        presenter = null
    }

}