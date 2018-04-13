package io.chthonic.mythos.kotlinexample.ui.layouts

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import io.chthonic.mythos.kotlinexample.App
import io.chthonic.mythos.kotlinexample.ui.fragments.RoFragment
import io.chthonic.mythos.kotlinexample.ui.presenters.DahPresenter
import io.chthonic.mythos.kotlinexample.ui.vus.DahVu
import io.chthonic.mythos.mvp.MVPDispatcher
import io.chthonic.mythos.mvp.MVPLayout
import io.chthonic.mythos.mvp.PresenterCacheBasic

/**
 * Created by jhavatar on 3/12/2016.
 */
class DahLayout : MVPLayout<DahPresenter, DahVu> {

    companion object {
        private val MVP_UID by lazy {
            DahLayout::class.java.simpleName.hashCode()
        }
    }

    override fun createMVPDispatcher(): MVPDispatcher<DahPresenter, DahVu> {
        return MVPDispatcher(MVP_UID,
                PresenterCacheBasic(DahPresenter()),
                ::DahVu)
    }

    override fun registerLifecycleCallback() {
        App.lifecycleManager.registerCallback(RoFragment.lIFECYCLE_KEY, lifecycleCallback)
    }

    override fun unregisterLifecycleCallback() {
        App.lifecycleManager.unregisterCallback(RoFragment.lIFECYCLE_KEY, lifecycleCallback)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)
}