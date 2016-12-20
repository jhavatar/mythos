package io.chthonic.mythos.mvp

/**
 * Created by jhavatar on 12/11/2016.
 */
interface PresenterCache<P> where P : Presenter<*>{
    var presenter: P?
}