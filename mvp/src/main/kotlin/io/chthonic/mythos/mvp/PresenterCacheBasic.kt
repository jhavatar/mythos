package io.chthonic.mythos.mvp

/**
 * Created by jhavatar on 12/22/2016.
 *
 * Most basic implementation of PresenterCache.
 */
class PresenterCacheBasic<P>(override var presenter: P?) : PresenterCache<P>() where P : Presenter<*>