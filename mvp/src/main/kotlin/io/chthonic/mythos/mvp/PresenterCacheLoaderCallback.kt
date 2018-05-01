package io.chthonic.mythos.mvp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.LoaderManager
import java.lang.ref.WeakReference
import java.util.concurrent.Callable

/**
 * Created by jhavatar on 12/11/2016.
 *
 * PresenterCache implementation based on LoaderManager.LoaderCallbacks, to be used in conjunction with Loader/PresenterLoader. PresenterLoader allows Presenter to survive rotation, i.e. activity recreation.
 * @param P type of Presenter returned.
 * @param context Context used to create PresenterLoader. Note, a weak reference is kept to this presenter.
 * @property createPresenterFunction function that is used to create Presenter.
 * @constructor create PresenterCacheLoaderCallback instance.
 */
class PresenterCacheLoaderCallback<P>(context: Context, val createPresenterFunction: () -> P) : PresenterCache<P>(), LoaderManager.LoaderCallbacks<P> where P : Presenter<*>{


    /**
     * @param context Context used to create PresenterLoader. Note, a weak reference is kept to this presenter.
     * @param createPresenterCallable callable that is used to create Presenter.
     */
    constructor(context: Context, createPresenterCallable: Callable<P>) : this(context, {createPresenterCallable.call()})

    /**
     * Presenter returned by get() method.
     */
    override var presenter: P? = null

    /**
     * Weak reference to context used to create PresenterLoader.
     */
    val contextRef:  WeakReference<Context> = WeakReference(context)


    override fun onCreateLoader(id: Int, arg: Bundle?): android.support.v4.content.Loader<P> {
        return PresenterLoader(contextRef.get()!!, createPresenterFunction)
    }

    override fun onLoadFinished(loader: android.support.v4.content.Loader<P>, presenter: P) {
        this.presenter = presenter
    }

    override fun onLoaderReset(loader: android.support.v4.content.Loader<P>) {
        remove()
    }
}