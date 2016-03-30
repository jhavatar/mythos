package io.chthonic.mythos.javaexample.ui.vus;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.chthonic.mythos.javaexample.R;
import io.chthonic.mythos.mvp.VuImpl;

/**
 * Created by jhavatar on 3/3/2016.
 */
public class FusVu extends VuImpl {

    public FusVu(LayoutInflater inflater,
          Activity activity,
          Fragment fragment,
          ViewGroup parentView) {
        super(inflater, activity, fragment, parentView);
    }

    @Override
    public int getRootViewLayoutId(){
       return R.layout.activity_fus;
    }
}