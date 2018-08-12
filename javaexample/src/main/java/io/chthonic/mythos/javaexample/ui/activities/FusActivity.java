package io.chthonic.mythos.javaexample.ui.activities;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import io.chthonic.mythos.javaexample.App;
import io.chthonic.mythos.javaexample.R;
import io.chthonic.mythos.javaexample.ui.fragments.RoFragment;
import io.chthonic.mythos.javaexample.ui.presenters.FusPresenter;
import io.chthonic.mythos.javaexample.ui.vus.FusVu;
import io.chthonic.mythos.mvp.FragmentLifecycleDispatcher;
import io.chthonic.mythos.mvp.MVPActivity;
import io.chthonic.mythos.mvp.MVPDispatcher;
import io.chthonic.mythos.mvp.PresenterCache;
import io.chthonic.mythos.mvp.PresenterCacheBasicLazy;
import io.chthonic.mythos.viewmodel.PesenterCacheViewModel;

public class FusActivity extends MVPActivity<FusPresenter, FusVu> {

    private static final int MVP_UID = FusActivity.class.getSimpleName().hashCode();

    private FragmentLifecycleDispatcher fragmentLifecycleDispatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Map<Class<? extends Fragment>, String> lifecycleKeyMap = new HashMap<>();
        lifecycleKeyMap.put(RoFragment.class, getResources().getString(R.string.ro_lifecycle_key));
        fragmentLifecycleDispatcher = new FragmentLifecycleDispatcher(lifecycleKeyMap);
        App.lifecycleManager.registerDispatcher(fragmentLifecycleDispatcher);
        getSupportFragmentManager().registerFragmentLifecycleCallbacks(fragmentLifecycleDispatcher, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // do after super.onDestroy() to still get the onDestroy call
        App.lifecycleManager.unregisterDispatcher(fragmentLifecycleDispatcher);
    }

    @NonNull
    @Override
    public MVPDispatcher<FusPresenter, FusVu> createMVPDispatcher() {
        @SuppressWarnings("unchecked")
        PesenterCacheViewModel<FusPresenter> viewModel = (PesenterCacheViewModel<FusPresenter>) ViewModelProviders.of(this).get(String.valueOf(MVP_UID), PesenterCacheViewModel.class);
        PresenterCache<FusPresenter> presenterCache = viewModel.getCache();
        if (presenterCache == null) {
            presenterCache = new PresenterCacheBasicLazy<>(new Callable<FusPresenter>() {
                @Override
                public FusPresenter call() throws Exception {
                    return new FusPresenter();
                }
            }, false);

            viewModel.setCache(presenterCache);
        }
        return new MVPDispatcher<>(MVP_UID,
                presenterCache,
                new MVPDispatcher.CreateVuFunction<FusVu>() {
                    @NonNull
                    @Override
                    public FusVu invoke(@NonNull LayoutInflater inflater, @NonNull Activity activity, @Nullable Fragment fragment, @Nullable ViewGroup parentView) {
                        return new FusVu(inflater, activity, fragment, parentView);
                    }
                });
    }
}
