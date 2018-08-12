package io.chthonic.mythos.javaexample.ui.presenters;

import android.os.Bundle;
import android.support.annotation.NonNull;

import io.chthonic.mythos.javaexample.ui.vus.RoVu;
import io.chthonic.mythos.mvp.Presenter;

/**
 * Created by jhavatar on 3/22/2016.
 */
public class RoPresenter extends Presenter<RoVu> {

    private boolean showDah = true;

    public RoPresenter() {
        super();
    }

    @Override
    public void onLink(@NonNull RoVu vu, Bundle inState, @NonNull Bundle args) {
        super.onLink(vu, inState, args);

        vu.updateDahDisplay(showDah);

        vu.setToggleDahListener(new Runnable() {

            @Override
            public void run() {
                showDah = !showDah;
                RoVu vu = getVu();
                if (vu != null) {
                    vu.updateDahDisplay(showDah);
                }
            }
        });
    }

}