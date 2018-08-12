package io.chthonic.mythos.javaexample.ui.presenters;

import android.os.Bundle;
import android.support.annotation.NonNull;

import io.chthonic.mythos.mvp.Presenter;
import io.chthonic.mythos.mvp.Vu;

/**
 * Created by jhavatar on 8/12/2018.
 */
public abstract class BasePresenter<V extends Vu> extends Presenter<V>  {
    private static String LINK_KEY = "link_key";
    private static String SAVE_KEY = "save_key";

    private int linkCount = 0;
    private int saveCount = 0;

    protected String getText(String name) {
        return name + ": linkCount = " + linkCount + ", saveCount = " + saveCount;
    }

    @Override
    public void onLink(@NonNull V vu, Bundle inState, @NonNull Bundle args) {
        super.onLink(vu, inState, args);
        if (inState != null) {
            linkCount = inState.getInt(LINK_KEY, linkCount);
            saveCount = inState.getInt(SAVE_KEY, saveCount);
        }
        linkCount++;
    }

    @Override
    public void onSaveState(@NonNull Bundle outState) {
        super.onSaveState(outState);
        saveCount++;
        outState.putInt(LINK_KEY, linkCount);
        outState.putInt(SAVE_KEY, saveCount);
    }
}
