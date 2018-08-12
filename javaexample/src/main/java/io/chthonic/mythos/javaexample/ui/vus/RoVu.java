package io.chthonic.mythos.javaexample.ui.vus;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.chthonic.mythos.javaexample.R;
import io.chthonic.mythos.javaexample.ui.layouts.DahLayout;
import io.chthonic.mythos.mvp.Vu;

/**
 * Created by jhavatar on 3/22/2016.
 */
public class RoVu extends Vu {

    private Runnable toggleDahListener = null;

    public RoVu(LayoutInflater layoutInflater,
                Activity activity,
                Fragment fragment,
                ViewGroup parentView) {

        super(layoutInflater, activity, fragment, parentView);
    }

    @Override
    public void onCreate() {
        Button toggleButton = (Button) this.getRootView().findViewById(R.id.button_toggle_dah);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable listener = toggleDahListener;
                if (listener != null) {
                    listener.run();
                }
            }
        });
    }

    @Override
    public int getRootViewLayoutId() {
        return R.layout.fragment_ro;
    }

    public Runnable getToggleDahListener() {
        return toggleDahListener;
    }

    public void setToggleDahListener(Runnable toggleDahListener) {
        this.toggleDahListener = toggleDahListener;
    }

    public void updateDahDisplay(boolean showDah) {
        ViewGroup rootLayout = (ViewGroup) getRootView();
        View dahLayout = getRootView().findViewById(R.id.dah_layout);
        if (showDah && (dahLayout == null)) {
            dahLayout = new DahLayout(getActivity(), getActivity().getResources().getString(R.string.ro_lifecycle_key));
            dahLayout.setId(R.id.dah_layout);
            rootLayout.addView(dahLayout);

        } else if (!showDah && (dahLayout != null)) {
            rootLayout.removeView(dahLayout);
        }
    }

}