package io.chthonic.mythos.javaexample.ui.vus;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

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
}
