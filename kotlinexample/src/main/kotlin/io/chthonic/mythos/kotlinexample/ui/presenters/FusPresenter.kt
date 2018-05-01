package io.chthonic.mythos.kotlinexample.ui.presenters

import android.os.Bundle
import io.chthonic.mythos.kotlinexample.ui.vus.FusVu
import io.chthonic.mythos.mvp.Presenter

/**
 * Created by jhavatar on 3/3/2016.
 */
class FusPresenter : Presenter<FusVu>() {
    var showRo: Boolean  = true
    var showDah: Boolean = true

    override fun onLink(vu: FusVu, inState: Bundle?, args: Bundle) {
        super.onLink(vu, inState, args)

        vu.updateRoDisplay(showRo)
        vu.updateDahDisplay(showDah)

        vu.toggleRoListener = {
            showRo = !showRo
            this.vu?.updateRoDisplay(showRo)
        }

        vu.toggleDahListener = {
            showDah = !showDah
            this.vu?.updateDahDisplay(showDah)
        }
    }
}