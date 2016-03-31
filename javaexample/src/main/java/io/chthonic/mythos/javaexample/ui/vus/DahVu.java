package io.chthonic.mythos.javaexample.ui.vus;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.chthonic.mythos.javaexample.R;
import io.chthonic.mythos.mvp.Vu;

/**
 * Created by jhavatar on 3/30/2016.
 */
public class DahVu extends Vu {

    public DahVu(@NotNull LayoutInflater inflater, @NotNull Activity activity, @Nullable Fragment fragment, @Nullable ViewGroup parentView) {
        super(inflater, activity, fragment, parentView);
    }

    @Override
    public int getRootViewLayoutId() {
        return R.layout.layout_dah;
    }
}
