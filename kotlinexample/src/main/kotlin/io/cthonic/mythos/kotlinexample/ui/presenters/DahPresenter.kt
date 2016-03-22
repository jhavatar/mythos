package io.cthonic.mythos.kotlinexample.ui.presenters

import io.cthonic.mythos.kotlinexample.ui.vus.FusVu
import io.cthonic.mythos.mvp.PresenterImpl

/**
 * Created by jhavatar on 3/22/2016.
 */
class DahPresenter() : PresenterImpl<FusVu>() {

    override fun attachVu(vu: FusVu) {
        super.attachVu(vu);

        vu.setTitle("DAH");
        vu.setBg("#1FCECB")
    }
}