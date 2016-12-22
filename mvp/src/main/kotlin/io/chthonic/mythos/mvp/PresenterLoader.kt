package io.chthonic.mythos.mvp

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.support.v4.content.Loader
import android.util.Log


/**
 * Created by jhavatar on 12/3/2016.
 */
class PresenterLoader<P>(context: Context?, val presenterFactory: () -> P) : Loader<P>(context) where P : Presenter<*>{

    private var presenter: P? = null

    override fun onStartLoading() {
        Log.d("mew", "onStartLoading: presenter = " + presenter);

        // If we already own an instance, simply deliver it.
        if (presenter != null) {
            deliverResult(presenter)
            return
        }

        // Otherwise, force a load
        forceLoad()
    }

    override fun onForceLoad() {
        Log.d("mew", "onForceLoad");

        // Create the Presenter using the Factory
        presenter = presenterFactory();

        // Deliver the result
        deliverResult(presenter)
    }

    override fun onReset() {
        Log.d("mew", "onReset");
        presenter = null
    }

}