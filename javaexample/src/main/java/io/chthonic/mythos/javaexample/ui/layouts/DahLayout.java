package io.chthonic.mythos.javaexample.ui.layouts;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.chthonic.mythos.javaexample.ui.presenters.DahPresenter;
import io.chthonic.mythos.javaexample.ui.vus.DahVu;
import io.chthonic.mythos.mvp.FragmentWrapper;
import io.chthonic.mythos.mvp.MVPDispatcher;
import io.chthonic.mythos.mvp.MVPLayout;
import io.chthonic.mythos.mvp.PresenterCacheBasic;
import kotlin.jvm.functions.Function4;

/**
 * Created by jhavatar on 3/12/2016.
 */
public class DahLayout extends MVPLayout<DahPresenter, DahVu> {

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
    protected MVPDispatcher<DahPresenter, DahVu> createMVPDispatcher() {
        return new MVPDispatcher<>(800,
                new PresenterCacheBasic<>(new DahPresenter()),
                new Function4<LayoutInflater, Activity, FragmentWrapper, ViewGroup, DahVu>() {
                    @Override
                    public DahVu invoke(LayoutInflater layoutInflater, Activity activity, FragmentWrapper fragmentWrapper, ViewGroup parentView) {
                        return new DahVu(layoutInflater, activity, fragmentWrapper, parentView);
                    }
                });
    }
}