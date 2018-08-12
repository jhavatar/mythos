package io.chthonic.mythos.mvp

/**
 * Created by jhavatar on 12/11/2016.
 *
 * Returns Presnter to be used by MVP pattern.
 * @param P type of Presenter it returns.
 */
interface PresenterCache<out P> where P : Presenter<*>{

    val mvpDispatcherShouldDestroy: Boolean

    /**
     * @return cached presenter instance
     */
    fun getCached(): P?

    /**
     * Remove presenter instance and call Presenters onDestroy() callback.
     */
    fun destroyCached()
}