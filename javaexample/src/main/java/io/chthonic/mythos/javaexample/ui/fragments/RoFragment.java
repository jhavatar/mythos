package io.chthonic.mythos.javaexample.ui.fragments;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.concurrent.Callable;

import io.chthonic.mythos.javaexample.ui.presenters.RoPresenter;
import io.chthonic.mythos.javaexample.ui.vus.RoVu;
import io.chthonic.mythos.mvp.MVPDispatcher;
import io.chthonic.mythos.mvp.MVPFragment;
import io.chthonic.mythos.mvp.PresenterCache;
import io.chthonic.mythos.mvp.PresenterCacheBasicLazy;
import io.chthonic.mythos.viewmodel.PesenterCacheViewModel;
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
        PesenterCacheViewModel<RoPresenter> viewModel = (PesenterCacheViewModel<RoPresenter>) ViewModelProviders.of(this).get(String.valueOf(MVP_UID), PesenterCacheViewModel.class);
        PresenterCache presenterCache = viewModel.getCache();
        if (presenterCache == null) {
            presenterCache = new PresenterCacheBasicLazy<RoPresenter>(new Callable<RoPresenter>() {
                @Override
                public RoPresenter call() throws Exception {
                    return new RoPresenter();
                }
            }, false);

            viewModel.setCache(presenterCache);
        }
        return new MVPDispatcher<>(MVP_UID,
                presenterCache, new Function4<LayoutInflater, Activity, Fragment, ViewGroup, RoVu>() {
            @Override
            public RoVu invoke(LayoutInflater layoutInflater, Activity activity, Fragment fragment, ViewGroup parentView) {
                return new RoVu(layoutInflater, activity, fragment, parentView);
            }
        });
    }
}
