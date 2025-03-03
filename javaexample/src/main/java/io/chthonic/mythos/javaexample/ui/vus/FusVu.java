package io.chthonic.mythos.javaexample.ui.vus;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.chthonic.mythos.javaexample.R;
import io.chthonic.mythos.javaexample.databinding.ActivityFusBinding;
import io.chthonic.mythos.javaexample.ui.fragments.RoFragment;
import io.chthonic.mythos.javaexample.ui.layouts.DahLayout;
import io.chthonic.mythos.mvp.Vu;

/**
 * Created by jhavatar on 3/3/2016.
 */
public class FusVu extends Vu<ActivityFusBinding> {

    private Runnable toggleRoListener = null;
    private Runnable toggleDahListener = null;

    public FusVu(LayoutInflater layoutInflater,
                 Activity activity,
                 Fragment fragment,
                 ViewGroup parentView) {
        super(layoutInflater, activity, fragment, parentView);
    }

    public Runnable getToggleRoListener() {
        return toggleRoListener;
    }

    public void setToggleRoListener(Runnable toggleRoListener) {
        this.toggleRoListener = toggleRoListener;
    }

    public Runnable getToggleDahListener() {
        return toggleDahListener;
    }

    public void setToggleDahListener(Runnable toggleDahListener) {
        this.toggleDahListener = toggleDahListener;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        getRootView().findViewById(R.id.button_toggle_ro).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Runnable listener = toggleRoListener;
                if (listener != null) {
                    listener.run();
                }
            }
        });

        getRootView().findViewById(R.id.button_toggle_dah).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Runnable listener = toggleDahListener;
                if (listener != null) {
                    listener.run();
                }
            }
        });
    }

    public void updateRoDisplay(boolean showRo) {
        FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(RoFragment.TAG);
        if (showRo && (fragment == null)) {
            fragment = new RoFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment, RoFragment.TAG)
                    .commit();

        } else if (!showRo && (fragment != null)) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
    }

    public void updateDahDisplay(boolean showDah) {
        ViewGroup childLayout = getRootView().findViewById(R.id.child_container);
        View dahLayout = childLayout.findViewById(R.id.dah_layout);
        if (showDah && (dahLayout == null)) {
            dahLayout = new DahLayout(getActivity(), getActivity().getResources().getString(R.string.fus_lifecycle_key));
            dahLayout.setId(R.id.dah_layout);
            childLayout.addView(dahLayout);

        } else if (!showDah && (dahLayout != null)) {
            childLayout.removeView(dahLayout);
        }
    }

    public void setText(String text) {
        ((TextView) getRootView().findViewById(R.id.fus_text)).setText(text);
    }
}