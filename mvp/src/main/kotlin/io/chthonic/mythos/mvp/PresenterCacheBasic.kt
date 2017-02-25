package io.chthonic.mythos.mvp

/**
 * Created by jhavatar on 12/22/2016.
 *
 * Most basic implementation of PresenterCache that returns Presenter passed to constructor and Presenter does not survive rotation.
 * @param P type Of Presenter returned.
 * @param presenter presenter returned with get() call.
 */
class PresenterCacheBasic<P>(override var presenter: P?) : PresenterCache<P>() where P : Presenter<*>