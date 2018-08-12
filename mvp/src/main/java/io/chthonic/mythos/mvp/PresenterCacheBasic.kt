package io.chthonic.mythos.mvp

/**
 * Created by jhavatar on 12/22/2016.
 *
 * Most basic implementation of PresenterCache that returns Presenter passed to constructor and Presenter does not survive rotation.
 * @param P type Of Presenter returned.
 * @param presenter Presenter returned by getCached()
 * @param mvpDispatcherShouldDestroy should MVPDispatcher call destroyCached().
 */
open class PresenterCacheBasic<P>(protected var presenter: P?, override val mvpDispatcherShouldDestroy: Boolean) : PresenterCache<P> where P : Presenter<*> {

    override fun getCached(): P? {
        return presenter
    }

    override fun destroyCached() {
        presenter?.onDestroy()
        presenter = null
    }
}