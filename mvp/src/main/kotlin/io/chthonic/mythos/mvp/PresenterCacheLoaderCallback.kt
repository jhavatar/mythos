package io.chthonic.mythos.mvp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.LoaderManager
import java.lang.ref.WeakReference
import java.util.concurrent.Callable

/**
 * Created by jhavatar on 12/11/2016.
 */
class PresenterCacheLoaderCallback<P>(context: Context, val createPresenter: () -> P) : PresenterCache<P>(), LoaderManager.LoaderCallbacks<P> where P : Presenter<*>{
    constructor(context: Context, callable: Callable<P>) : this(context, {callable.call()})

    override var presenter: P? = null

    val contextRef:  WeakReference<Context> = WeakReference(context)

    override fun onCreateLoader(id: Int, arg: Bundle?): android.support.v4.content.Loader<P> {
        return PresenterLoader(contextRef.get(), createPresenter)
    }

    override fun onLoadFinished(loader: android.support.v4.content.Loader<P>, presenter: P) {
        this.presenter = presenter
    }

    override fun onLoaderReset(loader: android.support.v4.content.Loader<P>) {
        remove()
    }
}