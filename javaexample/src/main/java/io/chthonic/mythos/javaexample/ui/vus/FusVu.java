package io.chthonic.mythos.javaexample.ui.vus;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import io.chthonic.mythos.javaexample.R;
import io.chthonic.mythos.mvp.VuImpl;

/**
 * Created by jhavatar on 3/3/2016.
 */
public class FusVu extends VuImpl {

    private TextView text;

    public FusVu(LayoutInflater inflater,
          Activity activity,
          Fragment fragment,
          ViewGroup parentView) {
        super(inflater, activity, fragment, parentView);
        text = (TextView) this.getRootView().findViewById(R.id.text);
    }

    @Override
    public int getRootViewLayoutId(){
       return R.layout.layout_fus;
    }

    public void setTitle(String text) {
        this.text.setText(text);
    }

    public void setBg(String color) {
        this.getRootView().setBackgroundColor(Color.parseColor(color));
    }
}