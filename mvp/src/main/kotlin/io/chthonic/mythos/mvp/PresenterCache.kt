package io.chthonic.mythos.mvp

/**
 * Created by jhavatar on 12/11/2016.
 *
 * Returns Presnter to be used by MVP pattern.
 * @param P type of PResenter it returns.
 */
abstract class PresenterCache<P> where P : Presenter<*>{
    abstract protected var presenter: P?

    /**
     * @return Presenter instance
     */
    open fun get(): P? {
        return presenter
    }

    /**
     * Remove presenter instance and call Presenters onDestroy() callback.
     */
    open fun remove() {
        presenter?.onDestroy()
        presenter = null
    }
}