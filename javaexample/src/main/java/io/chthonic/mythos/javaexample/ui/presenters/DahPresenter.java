package io.chthonic.mythos.javaexample.ui.presenters;

import android.support.annotation.NonNull;

import io.chthonic.mythos.javaexample.ui.vus.FusVu;
import io.chthonic.mythos.mvp.PresenterImpl;

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