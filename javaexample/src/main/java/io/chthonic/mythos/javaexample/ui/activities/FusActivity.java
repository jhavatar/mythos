package io.chthonic.mythos.javaexample.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;

import io.chthonic.mythos.javaexample.R;
import io.chthonic.mythos.javaexample.ui.fragments.RoFragment;
import io.chthonic.mythos.javaexample.ui.presenters.FusPresenter;
import io.chthonic.mythos.javaexample.ui.vus.FusVu;
import io.chthonic.mythos.mvp.FragmentWrapper;
import io.chthonic.mythos.mvp.MVPActivity;
import io.chthonic.mythos.mvp.MVPDispatcher;
import io.chthonic.mythos.mvp.PresenterCacheLoaderCallback;
import kotlin.jvm.functions.Function4;

public class FusActivity extends MVPActivity<FusPresenter, FusVu> {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState);
        }
        if (fragment == null) {
            Fragment tempFragment = getSupportFragmentManager().findFragmentByTag(RoFragment.TAG);
            if (tempFragment != null) {
                getSupportFragmentManager().beginTransaction().remove(tempFragment).commit();
            }

            fragment = new RoFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.child_container, fragment, RoFragment.TAG)
                    .commit();
        }
    }

    @NotNull
    @Override
    public MVPDispatcher<FusPresenter, FusVu> createMVPDispatcher() {
        return new MVPDispatcher<FusPresenter, FusVu>(1221,
                new PresenterCacheLoaderCallback<FusPresenter>(this, new Callable<FusPresenter>() {

                    @Override
                    public FusPresenter call() {
                        return new FusPresenter();
                    }
                }),

                new Function4<LayoutInflater, Activity, FragmentWrapper, ViewGroup, FusVu>() {
                    @Override
                    public FusVu invoke(LayoutInflater inflater, Activity activity, FragmentWrapper fragmentWrapper, ViewGroup parentView) {
                        return new FusVu(inflater, activity, fragmentWrapper, parentView);
                    }
                });
//        {
//
//            @Override
//            public int getUid() {
//                return 1221;
//            }
//
//            @NotNull
//            @Override
//            protected FusVu createVu(@NotNull LayoutInflater inflater, @NotNull Activity activity, @Nullable FragmentWrapper fragment, @Nullable ViewGroup parentView) {
//                return new FusVu(inflater, activity, fragment, parentView);
//            }
//        };
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (fragment != null) {
            getSupportFragmentManager().putFragment(outState, RoFragment.TAG, fragment);
        }
    }

    protected void restoreInstanceState(Bundle inState){
        if (inState != null) {
            if (inState.containsKey(RoFragment.TAG)) {
                fragment = getSupportFragmentManager().getFragment(inState, RoFragment.TAG);
            }
        }
    }

}
