package io.cthonic.mythos.kotlinexample.ui.presenters

import io.cthonic.mythos.kotlinexample.ui.vus.FusVu
import io.cthonic.mythos.mvp.PresenterImpl

/**
 * Created by jhavatar on 3/3/2016.
 */
class FusPresenter() : PresenterImpl<FusVu>() {

    override fun attachVu(vu: FusVu) {
        super.attachVu(vu);

        vu.setTitle("FUS");
        vu.setBg("#FC6C85")
    }
}