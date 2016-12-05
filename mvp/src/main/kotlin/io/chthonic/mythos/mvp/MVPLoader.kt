package io.chthonic.mythos.mvp

import android.content.Context
import android.content.Loader

/**
 * Created by jhavatar on 12/4/2016.
 */
class MVPLoader <MVPDispatcher>(context: Context?) : Loader<MVPDispatcher>(context){
    private var presenter: Presenter<*>? = null

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
        presenter = presenterFactory();

        // Deliver the result
        deliverResult(presenter)
    }

    override fun onReset() {
        presenter?.onDestroy()
        presenter = null
    }
}