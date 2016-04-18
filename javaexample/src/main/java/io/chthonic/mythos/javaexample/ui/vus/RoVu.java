package io.chthonic.mythos.javaexample.ui.vus;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.chthonic.mythos.javaexample.R;
import io.chthonic.mythos.javaexample.ui.layouts.DahLayout;
import io.chthonic.mythos.mvp.FragmentWrapper;
import io.chthonic.mythos.mvp.Vu;

/**
 * Created by jhavatar on 3/22/2016.
 */
public class RoVu extends Vu {

    public RoVu(LayoutInflater inflater,
                Activity activity,
                FragmentWrapper fragment,
                ViewGroup parentView) {
        super(inflater, activity, fragment, parentView);

        Button toggleButton = (Button) this.getRootView().findViewById(R.id.button_toggle_dah);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View dahLayout = getRootView().findViewById(R.id.dah_layout);

                if (dahLayout != null) {
                    ((ViewGroup)  getRootView()).removeView(dahLayout);
                } else {
                    dahLayout = new DahLayout(getActivity(), null);
                    dahLayout.setId(R.id.dah_layout);
                    ((ViewGroup) getRootView()).addView(dahLayout);
                }
            }
        });
    }

    @Override
    public int getRootViewLayoutId() {
        return R.layout.fragment_ro;
    }
}