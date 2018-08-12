package io.chthonic.mythos.javaexample.ui.presenters;

import android.os.Bundle;
import android.support.annotation.NonNull;

import io.chthonic.mythos.javaexample.ui.vus.FusVu;

/**
 * Created by jhavatar on 3/3/2016.
 */
public class FusPresenter extends BasePresenter<FusVu> {

    private boolean showRo = true;
    private boolean showDah = true;

    public FusPresenter() {
        super();
    }

    @Override
    public void onLink(@NonNull FusVu vu, Bundle inState, @NonNull Bundle args) {
        super.onLink(vu, inState, args);

        vu.updateRoDisplay(showRo);
        vu.updateDahDisplay(showDah);

        vu.setToggleRoListener(new Runnable() {

            @Override
            public void run() {
                showRo = !showRo;
                FusVu vu = getVu();
                if (vu != null) {
                    vu.updateRoDisplay(showRo);
                }
            }
        });

        vu.setToggleDahListener(new Runnable() {

            @Override
            public void run() {
                showDah = !showDah;
                FusVu vu = getVu();
                if (vu != null) {
                    vu.updateDahDisplay(showDah);
                }
            }
        });

        vu.setText(getText("FUS"));
    }
}