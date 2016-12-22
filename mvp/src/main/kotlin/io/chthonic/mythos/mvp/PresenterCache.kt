package io.chthonic.mythos.mvp

/**
 * Created by jhavatar on 12/11/2016.
 */
abstract class PresenterCache<P> where P : Presenter<*>{
    abstract protected var presenter: P?

    open fun get(): P? {
        return presenter
    }

    open fun remove() {
        presenter?.onDestroy()
        presenter = null;
    }
}