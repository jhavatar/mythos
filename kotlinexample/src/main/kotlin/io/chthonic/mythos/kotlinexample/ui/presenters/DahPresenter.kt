package io.chthonic.mythos.kotlinexample.ui.presenters

import io.chthonic.mythos.kotlinexample.ui.vus.FusVu
import io.chthonic.mythos.mvp.PresenterImpl

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