package io.chthonic.mythos.javaexample.ui.layouts;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.chthonic.mythos.javaexample.ui.presenters.DahPresenter;
import io.chthonic.mythos.javaexample.ui.vus.FusVu;
import io.chthonic.mythos.mvp.MVPDispatcher;
import io.chthonic.mythos.mvp.MVPLayout;

/**
 * Created by jhavatar on 3/12/2016.
 */
public class DahLayout extends MVPLayout<DahPresenter, FusVu> {

    public DahLayout(@Nullable Context context) {
        super(context);
    }

    public DahLayout(@Nullable Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DahLayout(@Nullable Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DahLayout(@Nullable Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @NotNull
    @Override
    protected MVPDispatcher<DahPresenter, FusVu> createMVPDispatcher() {
        return new MVPDispatcher<DahPresenter, FusVu>() {

            @NotNull
            @Override
            protected FusVu createVu(@NotNull LayoutInflater inflater, @NotNull Activity activity, @Nullable Fragment fragment, @Nullable ViewGroup parentView) {
                return new FusVu(inflater, activity, fragment, parentView);
            }

            @NotNull
            @Override
            protected DahPresenter createPresenter() {
                return new DahPresenter();
            }
        };
    }
}