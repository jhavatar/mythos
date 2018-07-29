package io.chthonic.mythos.mvp

import java.util.concurrent.Callable

/**
 * Created by jhavatar on 7/29/2018.
 */
class PresenterCacheLazy<P>(oneTimeCreatePresenterFunction: ()->P) : PresenterCache<P>() where P : Presenter<*>{

    constructor(oneTimeCreatePresenterCallable: Callable<P>) : this({ oneTimeCreatePresenterCallable.call()})

    private var presenterCreator: (()->P)? = oneTimeCreatePresenterFunction

    override var presenter: P? = null
        get() {
            if (field == null) {
                presenterCreator?.let{
                    field = it()
                }
                presenterCreator = null
            }
            return field
        }
}