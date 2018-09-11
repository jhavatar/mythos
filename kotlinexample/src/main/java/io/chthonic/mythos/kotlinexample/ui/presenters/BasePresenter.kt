package io.chthonic.mythos.kotlinexample.ui.presenters

import android.os.Bundle
import io.chthonic.mythos.mvp.Presenter
import io.chthonic.mythos.mvp.Vu

/**
 * Created by jhavatar on 8/12/2018.
 */
abstract class BasePresenter<V>: Presenter<V>() where V : Vu {
    companion object {
        private const val SAVE_KEY = "save_key"
    }
    private var saveCount = 0

    protected fun getText(name: String): String {
        return "$name: onSaveState count = $saveCount, firstLink = $firstLink, firstLinkWithVu = $firstLinkWithVu"
    }

    override fun onLink(vu: V, inState: Bundle?, args: Bundle) {
        super.onLink(vu, inState, args)
        saveCount = inState?.getInt(SAVE_KEY, saveCount) ?: saveCount
    }

    override fun onSaveState(outState: Bundle) {
        super.onSaveState(outState)
        saveCount++
        outState.putInt(SAVE_KEY, saveCount)
    }
}