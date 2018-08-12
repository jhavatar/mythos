package io.chthonic.mythos.mvp

import java.util.concurrent.Callable

/**
 * Created by jhavatar on 7/29/2018.
 * @param P type Of Presenter returned.
 * @param oneTimeCreatePresenterFunction creates initial Presenter returned by getCached()
 * @param mvpDispatcherShouldDestroy should MVPDispatcher call destroyCached().
 */
class PresenterCacheBasicLazy<out P>(oneTimeCreatePresenterFunction: ()->P, override val mvpDispatcherShouldDestroy: Boolean) : PresenterCache<P> where P : Presenter<*>{

    constructor(oneTimeCreatePresenterCallable: Callable<P>, mvpDispatcherShouldDestroy: Boolean) : this({ oneTimeCreatePresenterCallable.call()}, mvpDispatcherShouldDestroy)

    private var presenterCreator: (()->P)? = oneTimeCreatePresenterFunction

    private var presenter: P? = null
        get() {
            if (field == null) {
                presenterCreator?.let{
                    field = it()
                }
                presenterCreator = null
            }
            return field
        }

    override fun getCached(): P? {
        return presenter
    }

    override fun destroyCached() {
        presenterCreator = null
        presenter?.onDestroy()
        presenter = null
    }
}