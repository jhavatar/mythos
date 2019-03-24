package io.chthonic.mythos.javaexample.ui.presenters;

import android.os.Bundle;
import androidx.annotation.NonNull;

import io.chthonic.mythos.javaexample.ui.vus.DahVu;

/**
 * Created by jhavatar on 3/22/2016.
 */
public class DahPresenter extends BasePresenter<DahVu> {

    public DahPresenter() {
        super();
    }

    @Override
    public void onLink(@NonNull DahVu vu, Bundle inState, @NonNull Bundle args) {
        super.onLink(vu, inState, args);
        vu.setText(getText("DAH"));
    }
}