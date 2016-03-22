package io.cthonic.mythos.javaexample.ui.presenters;

import android.support.annotation.NonNull;

import io.cthonic.mythos.mvp.PresenterImpl;
import io.cthonic.mythos.javaexample.ui.vus.FusVu;

/**
 * Created by jhavatar on 3/3/2016.
 */
public class FusPresenter extends PresenterImpl<FusVu> {

    public void attachVu(@NonNull FusVu vu) {
        super.attachVu(vu);

        vu.setTitle("FUS");
        vu.setBg("#FC6C85");
    }
}