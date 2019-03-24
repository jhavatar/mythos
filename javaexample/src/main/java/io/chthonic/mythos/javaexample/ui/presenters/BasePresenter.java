package io.chthonic.mythos.javaexample.ui.presenters;

import android.os.Bundle;
import androidx.annotation.NonNull;

import io.chthonic.mythos.mvp.Presenter;
import io.chthonic.mythos.mvp.Vu;

/**
 * Created by jhavatar on 8/12/2018.
 */
public abstract class BasePresenter<V extends Vu> extends Presenter<V>  {
    private static final String SAVE_KEY = "save_key";

    private int saveCount = 0;

    protected String getText(String name) {
        return name + ": onSaveState count = " + saveCount + ", firstLink = " + getFirstLink() + ", firstLinkWithVu = " + getFirstLinkWithVu();
    }

    @Override
    public void onLink(@NonNull V vu, Bundle inState, @NonNull Bundle args) {
        super.onLink(vu, inState, args);
        if (inState != null) {
            saveCount = inState.getInt(SAVE_KEY, saveCount);
        }
    }

    @Override
    public void onSaveState(@NonNull Bundle outState) {
        super.onSaveState(outState);
        saveCount++;
        outState.putInt(SAVE_KEY, saveCount);
    }
}
