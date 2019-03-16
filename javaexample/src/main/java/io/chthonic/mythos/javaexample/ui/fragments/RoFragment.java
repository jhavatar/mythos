package io.chthonic.mythos.javaexample.ui.fragments;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.chthonic.mythos.javaexample.ui.presenters.RoPresenter;
import io.chthonic.mythos.javaexample.ui.vus.RoVu;
import io.chthonic.mythos.mvp.MVPDispatcher;
import io.chthonic.mythos.mvp.MVPFragment;
import io.chthonic.mythos.viewmodel.PesenterCacheViewModel;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function4;

/**
 * Created by jhavatar on 3/22/2016.
 */
public class RoFragment extends MVPFragment<RoPresenter, RoVu> {

    public static final String TAG = RoFragment.class.getSimpleName();
    private static final int MVP_UID = TAG.hashCode();

    @NonNull
    @Override
    public MVPDispatcher<RoPresenter, RoVu> createMVPDispatcher() {
        return new MVPDispatcher<>(MVP_UID,
                PesenterCacheViewModel.getViewModelPresenterCache(this, MVP_UID, new Function0<RoPresenter>() {
                    @Override
                    public RoPresenter invoke() {
                        return new RoPresenter();
                    }
                }),
                new Function4<LayoutInflater, Activity, Fragment, ViewGroup, RoVu>() {
                    @Override
                    public RoVu invoke(LayoutInflater layoutInflater, Activity activity, Fragment fragment, ViewGroup parentView) {
                        return new RoVu(layoutInflater, activity, fragment, parentView);
                    }
                });
    }
}
