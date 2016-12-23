package io.chthonic.mythos.mvp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.util.Log
import java.lang.ref.WeakReference
import java.util.concurrent.Callable

/**
 * Created by jhavatar on 12/11/2016.
 */
class PresenterCacheLoaderCallback<P>(context: Context, val createPresenter: () -> P) : PresenterCache<P>(), LoaderManager.LoaderCallbacks<P> where P : Presenter<*>{
    constructor(context: Context, callable: Callable<P>) : this(context, {callable.call()})

    override var presenter: P? = null

    val contextRef:  WeakReference<Context>

    init {
        Log.d("mew", "init: context = " + context);
        contextRef = WeakReference(context)
        Log.d("mew", "init: contextRef = " + contextRef + ", context = " + contextRef.get());
    }

    override fun onCreateLoader(id: Int, arg: Bundle?): android.support.v4.content.Loader<P> {
        Log.d("mew", "onCreateLoader");
        return PresenterLoader<P>(contextRef.get(), createPresenter) as (android.support.v4.content.Loader<P>)
    }

    override fun onLoadFinished(loader: android.support.v4.content.Loader<P>, presenter: P) {
        Log.d("mew", "onLoadFinished");
        this.presenter = presenter
    }

    override fun onLoaderReset(loader: android.support.v4.content.Loader<P>) {
        Log.d("mew", "onLoaderReset");
        remove();
    }
}