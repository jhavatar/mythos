package io.chthonic.mythos.mvp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.LoaderManager
import java.lang.ref.WeakReference

/**
 * Created by jhavatar on 12/11/2016.
 */
class PresenterLoaderCallbackCache<P>(context: Context, val createPresenter: () -> P) : LoaderManager.LoaderCallbacks<P>, PresenterCache<P> where P : Presenter<*>{

    override var presenter: P? = null

    val contextRef:  WeakReference<Context>

    init {
        contextRef = WeakReference(context)
    }

    override fun onCreateLoader(id: Int, arg: Bundle): android.support.v4.content.Loader<P> {
        return PresenterLoader<P>(contextRef.get(), createPresenter) as (android.support.v4.content.Loader<P>)
    }

    override fun onLoadFinished(loader: android.support.v4.content.Loader<P>, presenter: P) {
        this.presenter = presenter
    }

    override fun onLoaderReset(loader: android.support.v4.content.Loader<P>) {
        presenter!!.onDestroy();
        presenter = null
    }
}