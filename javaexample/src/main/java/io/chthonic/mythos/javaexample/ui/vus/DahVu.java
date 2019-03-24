package io.chthonic.mythos.javaexample.ui.vus;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import io.chthonic.mythos.javaexample.R;
import io.chthonic.mythos.mvp.Vu;

/**
 * Created by jhavatar on 3/30/2016.
 */
public class DahVu extends Vu {

    public DahVu(@NonNull LayoutInflater layoutInflater, @NonNull Activity activity, @Nullable Fragment fragment, @Nullable ViewGroup parentView) {
        super(layoutInflater, activity, fragment, parentView);
    }

    @Override
    public int getRootViewLayoutId() {
        return R.layout.layout_dah;
    }

    public void setText(String text) {
        ((TextView) getRootView().findViewById(R.id.dah_text)).setText(text);
    }
}
