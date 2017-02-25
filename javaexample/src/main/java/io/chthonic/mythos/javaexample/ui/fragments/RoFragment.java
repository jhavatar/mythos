package io.chthonic.mythos.javaexample.ui.fragments;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;

import io.chthonic.mythos.javaexample.ui.presenters.RoPresenter;
import io.chthonic.mythos.javaexample.ui.vus.RoVu;
import io.chthonic.mythos.mvp.FragmentWrapper;
import io.chthonic.mythos.mvp.MVPDispatcher;
import io.chthonic.mythos.mvp.MVPFragment;
import io.chthonic.mythos.mvp.PresenterCacheLoaderCallback;
import kotlin.jvm.functions.Function4;

/**
 * Created by jhavatar on 3/22/2016.
 */
public class RoFragment extends MVPFragment<RoPresenter, RoVu> {

    public static final String TAG = RoFragment.class.getSimpleName();
    private static final int MVP_UID = TAG.hashCode();



    @NotNull
    @Override
    public MVPDispatcher<RoPresenter, RoVu> createMVPDispatcher() {
        return new MVPDispatcher<>(MVP_UID,
                new PresenterCacheLoaderCallback<>(this.getContext(), new Callable<RoPresenter>() {

                    @Override
                    public RoPresenter call() {
                        return new RoPresenter();
                    }
                }), new Function4<LayoutInflater, Activity, FragmentWrapper, ViewGroup, RoVu>() {
            @Override
            public RoVu invoke(LayoutInflater layoutInflater, Activity activity, FragmentWrapper fragmentWrapper, ViewGroup parentView) {
                return new RoVu(layoutInflater, activity, fragmentWrapper, parentView);
            }
        });
    }
}
