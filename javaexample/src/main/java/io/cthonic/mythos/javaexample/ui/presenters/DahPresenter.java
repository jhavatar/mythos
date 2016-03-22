package io.cthonic.mythos.javaexample.ui.presenters;

import android.support.annotation.NonNull;

import io.cthonic.mythos.javaexample.ui.vus.FusVu;
import io.cthonic.mythos.mvp.PresenterImpl;

/**
 * Created by jhavatar on 3/22/2016.
 */
public class DahPresenter extends PresenterImpl<FusVu> {

    public DahPresenter() {
        super();
    }

    @Override
     public void attachVu(@NonNull FusVu vu) {
        super.attachVu(vu);

        vu.setTitle("DAH");
        vu.setBg("#1FCECB");
    }
}