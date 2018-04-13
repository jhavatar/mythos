package io.chthonic.mythos.kotlinexample.ui.fragments

import android.os.Bundle
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import io.chthonic.mythos.kotlinexample.App
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
        val TAG: String by lazy {
            RoFragment::class.java.simpleName
        }

        private val MVP_UID by lazy {
            TAG.hashCode()
        }

        val lIFECYCLE_KEY = "ro"
    }

    private lateinit var  refwatcher: RefWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        refwatcher = LeakCanary.install(App.app)
    }

    override fun createMVPDispatcher(): MVPDispatcher<RoPresenter, RoVu> {
        return MVPDispatcher(MVP_UID,
                PresenterCacheLoaderCallback(this.activity!!, { RoPresenter() }),
                ::RoVu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        refwatcher.watch(this)
    }

}