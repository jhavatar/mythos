package io.chthonic.mythos.javaexample.ui.vus;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.chthonic.mythos.javaexample.R;
import io.chthonic.mythos.mvp.FragmentWrapper;
import io.chthonic.mythos.mvp.Vu;

/**
 * Created by jhavatar on 3/3/2016.
 */
public class FusVu extends Vu {

    public FusVu(LayoutInflater layoutInflater,
                 Activity activity,
                 FragmentWrapper fragmentWrapper,
                 ViewGroup parentView) {
        super(layoutInflater, activity, fragmentWrapper, parentView);
    }

    @Override
    public int getRootViewLayoutId(){
        return R.layout.activity_fus;
    }
}