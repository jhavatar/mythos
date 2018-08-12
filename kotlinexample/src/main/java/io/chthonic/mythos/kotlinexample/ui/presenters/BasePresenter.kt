package io.chthonic.mythos.kotlinexample.ui.presenters

import android.os.Bundle
import io.chthonic.mythos.mvp.Presenter
import io.chthonic.mythos.mvp.Vu

/**
 * Created by jhavatar on 8/12/2018.
 */
abstract class BasePresenter<V>: Presenter<V>() where V : Vu {
    companion object {
        private const val LINK_KEY = "link_key"
        private const val SAVE_KEY = "save_key"
    }
    private var linkCount = 0
    private var saveCount = 0

    protected fun getText(name: String): String {
        return "$name: linkCount = $linkCount, saveCount = $saveCount"
    }

    override fun onLink(vu: V, inState: Bundle?, args: Bundle) {
        super.onLink(vu, inState, args)
        linkCount = inState?.getInt(LINK_KEY, linkCount) ?: linkCount
        saveCount = inState?.getInt(SAVE_KEY, saveCount) ?: saveCount
        linkCount++
    }

    override fun onSaveState(outState: Bundle) {
        super.onSaveState(outState)
        saveCount++
        outState.putInt(LINK_KEY, linkCount)
        outState.putInt(SAVE_KEY, saveCount)
    }
}