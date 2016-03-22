package io.cthonic.mythos.javaexample.ui.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.cthonic.mythos.javaexample.ui.presenters.RoPresenter;
import io.cthonic.mythos.javaexample.ui.vus.RoVu;
import io.cthonic.mythos.mvp.MVPDispatcher;
import io.cthonic.mythos.mvp.MVPFragment;

/**
 * Created by jhavatar on 3/22/2016.
 */
public class RoFragment extends MVPFragment<RoPresenter, RoVu> {

    public static final String TAG = "RoFragment";

    @NotNull
    @Override
    public MVPDispatcher<RoPresenter, RoVu> createMVPDispatcher() {
        return new MVPDispatcher<RoPresenter, RoVu>() {
            @NotNull
            @Override
            protected RoPresenter createPresenter() {
                return new RoPresenter();
            }

            @NotNull
            @Override
            protected RoVu createVu(@NotNull LayoutInflater inflater, @NotNull Activity activity, @Nullable Fragment fragment, @Nullable ViewGroup parentView) {
                return new RoVu(inflater, activity, fragment, parentView);
            }
        };
    }
}
