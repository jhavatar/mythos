package io.chthonic.mythos.javaexample.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import io.chthonic.mythos.javaexample.App;
import io.chthonic.mythos.javaexample.R;
import io.chthonic.mythos.javaexample.ui.fragments.RoFragment;
import io.chthonic.mythos.javaexample.ui.presenters.FusPresenter;
import io.chthonic.mythos.javaexample.ui.vus.FusVu;
import io.chthonic.mythos.mvp.FragmentWrapper;
import io.chthonic.mythos.mvp.MVPActivity;
import io.chthonic.mythos.mvp.MVPDispatcher;
import io.chthonic.mythos.mvp.PresenterCacheLoaderCallback;
import io.chthonic.mythos.mvp.SupportFragmentLifecycleDispatcher;

public class FusActivity extends MVPActivity<FusPresenter, FusVu> {

    private static final int MVP_UID = FusActivity.class.getSimpleName().hashCode();

    private SupportFragmentLifecycleDispatcher fragmentLifecycleDispatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Map<Class<? extends Fragment>, String> lifecycleKeyMap = new HashMap();
        lifecycleKeyMap.put(RoFragment.class, getResources().getString(R.string.ro_lifecycle_key));
        fragmentLifecycleDispatcher = new SupportFragmentLifecycleDispatcher(lifecycleKeyMap);
        App.lifecycleManager.registerDispatcher(fragmentLifecycleDispatcher);
        getSupportFragmentManager().registerFragmentLifecycleCallbacks(fragmentLifecycleDispatcher, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // do after super.onDestroy() to still get the onDestroy call
        App.lifecycleManager.unregisterDispatcher(fragmentLifecycleDispatcher);
    }

    @NotNull
    @Override
    public MVPDispatcher<FusPresenter, FusVu> createMVPDispatcher() {
        return new MVPDispatcher<>(MVP_UID,
                new PresenterCacheLoaderCallback<>(this, new Callable<FusPresenter>() {

                    @Override
                    public FusPresenter call() {
                        return new FusPresenter();
                    }
                }),

                new MVPDispatcher.CreateVuFunction<FusVu>() {
                    @NotNull
                    @Override
                    public FusVu invoke(@NotNull LayoutInflater inflater, @NotNull Activity activity, @Nullable FragmentWrapper fragmentWrapper, @Nullable ViewGroup parentView) {
                        return new FusVu(inflater, activity, fragmentWrapper, parentView);
                    }
                });
    }
}
