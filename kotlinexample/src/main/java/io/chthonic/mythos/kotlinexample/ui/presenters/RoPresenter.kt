package io.chthonic.mythos.kotlinexample.ui.presenters

import android.os.Bundle
import io.chthonic.mythos.kotlinexample.ui.vus.RoVu

/**
 * Created by jhavatar on 3/22/2016.
 */
class RoPresenter : BasePresenter<RoVu>() {

    var showDah: Boolean = true

    override fun onLink(vu: RoVu, inState: Bundle?, args: Bundle) {
        super.onLink(vu, inState, args)

        vu.updateDahDisplay(showDah)

        vu.toggleDahListener = {
            showDah = !showDah
            this.vu?.updateDahDisplay(showDah)
        }

        vu.setText(getText("ROH"))
    }
}