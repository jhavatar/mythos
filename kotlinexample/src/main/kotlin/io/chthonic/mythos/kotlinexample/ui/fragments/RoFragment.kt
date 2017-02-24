package io.chthonic.mythos.kotlinexample.ui.fragments

import io.chthonic.mythos.kotlinexample.ui.presenters.RoPresenter
import io.chthonic.mythos.kotlinexample.ui.vus.RoVu
import io.chthonic.mythos.mvp.MVPDispatcher
import io.chthonic.mythos.mvp.MVPFragment
import io.chthonic.mythos.mvp.PresenterCacheLoaderCallback

/**
 * Created by jhavatar on 3/22/2016.
 */
class RoFragment : MVPFragment<RoPresenter, RoVu>() {

    companion object {
        val TAG by lazy {
            RoFragment::class.java.simpleName
        }
        private val MVP_UID by lazy {
            TAG.hashCode()
        }
    }

    override fun createMVPDispatcher(): MVPDispatcher<RoPresenter, RoVu> {
        return MVPDispatcher(MVP_UID,
                PresenterCacheLoaderCallback(this.activity, { RoPresenter() }),
                ::RoVu)
    }

}