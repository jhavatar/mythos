package io.chthonic.mythos.javaexample.ui.layouts;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.chthonic.mythos.javaexample.App;
import io.chthonic.mythos.javaexample.ui.presenters.DahPresenter;
import io.chthonic.mythos.javaexample.ui.vus.DahVu;
import io.chthonic.mythos.mvp.MVPDispatcher;
import io.chthonic.mythos.mvp.MVPLayout;
import io.chthonic.mythos.mvp.PresenterCacheBasic;
import kotlin.jvm.functions.Function4;

/**
 * Created by jhavatar on 3/12/2016.
 */
public class DahLayout extends MVPLayout<DahPresenter, DahVu> {

    private static final int MVP_UID = DahLayout.class.getSimpleName().hashCode();

    public DahLayout(@NonNull Context context) {
        super(context);
    }

    public DahLayout(@NonNull Context context, String lifecycleCallbackKey) {
        super(context, lifecycleCallbackKey);
    }

    public DahLayout(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DahLayout(@NonNull Context context, @NonNull AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DahLayout(@NonNull Context context, @NonNull AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @NonNull
    @Override
    protected MVPDispatcher<DahPresenter, DahVu> createMVPDispatcher() {
        return new MVPDispatcher<>(MVP_UID,
                new PresenterCacheBasic<>(new DahPresenter(), true),
                new Function4<LayoutInflater, Activity, Fragment, ViewGroup, DahVu>() {
                    @Override
                    public DahVu invoke(LayoutInflater layoutInflater, Activity activity, Fragment fragment, ViewGroup parentView) {
                        return new DahVu(layoutInflater, activity, fragment, parentView);
                    }
                });
    }


    @Override
    protected void registerLifecycleCallback() {
        String key = getLifecycleCallbackKey();
        if (key != null) {
            App.lifecycleManager.registerCallback(key, this.getLifecycleCallback());
        }
    }

    @Override
    protected void unregisterLifecycleCallback() {
        String key = getLifecycleCallbackKey();
        if (key != null) {
            App.lifecycleManager.unregisterCallback(key, this.getLifecycleCallback());
        }
    }
}