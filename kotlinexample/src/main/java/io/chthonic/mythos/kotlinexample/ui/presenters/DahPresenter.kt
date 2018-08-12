package io.chthonic.mythos.kotlinexample.ui.presenters

import android.os.Bundle
import io.chthonic.mythos.kotlinexample.ui.vus.DahVu

/**
 * Created by jhavatar on 3/22/2016.
 */
class DahPresenter : BasePresenter<DahVu>() {

    override fun onLink(vu: DahVu, inState: Bundle?, args: Bundle) {
        super.onLink(vu, inState, args)
        vu.setText(getText("DAH"))
    }
}