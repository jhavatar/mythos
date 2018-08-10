package io.chthonic.mythos.mvp

import java.util.concurrent.Callable

/**
 * Created by jhavatar on 7/29/2018.
 */
class PresenterCacheBasicLazy<out P>(oneTimeCreatePresenterFunction: ()->P) : PresenterCache<P> where P : Presenter<*>{

    constructor(oneTimeCreatePresenterCallable: Callable<P>) : this({ oneTimeCreatePresenterCallable.call()})

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

    override fun get(): P? {
        return presenter
    }

    override fun clear() {
        presenter?.onDestroy()
        presenter = null
    }
}