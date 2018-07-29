package io.chthonic.mythos.mvp

/**
 * Created by jhavatar on 7/29/2018.
 */
class PresenterCacheLazy<P>(oneTimePresenterCreator: ()->P) : PresenterCache<P>() where P : Presenter<*>{
    private var presenterCreator: (()->P)? = oneTimePresenterCreator

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