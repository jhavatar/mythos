package io.chthonic.mythos.mvp

import android.content.Context
import android.support.v4.content.Loader


/**
 * Created by jhavatar on 12/3/2016.
 *
 * Loader used to keep Presenter instance on configuration and orientation changes.
 * @param P type of Presenter in "loads".
 * @param context context required by Loader super class.
 * @property presenterFactory creates presenter instance.
 */
class PresenterLoader<P>(context: Context?, val presenterFactory: () -> P) : Loader<P>(context) where P : Presenter<*>{

    private var presenter: P? = null

    override fun onStartLoading() {

        // If we already own an instance, simply deliver it.
        if (presenter != null) {
            deliverResult(presenter)
            return
        }

        // Otherwise, force a load
        forceLoad()
    }

    override fun onForceLoad() {

        // Create the Presenter using the Factory
        presenter = presenterFactory()

        // Deliver the result
        deliverResult(presenter)
    }

    override fun onReset() {
        presenter = null
    }

}