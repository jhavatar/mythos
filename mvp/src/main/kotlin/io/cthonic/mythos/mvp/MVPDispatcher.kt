package io.cthonic.mythos.mvp

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by jhavatar on 3/9/2016.
 */
abstract class MVPDispatcher<P, V>() where P : Presenter<V>, V : Vu {
    var vu: V? = null;

    val presenter: P by lazy {
        createPresenter();
    }

    protected abstract fun createPresenter(): P;

    protected abstract fun createVu(inflater: LayoutInflater,
                                    activity: Activity,
                                    fragment: Fragment? = null,
                                    parentView: ViewGroup? = null): V;

    fun attachPresenter(activity: Activity,
                        fragment: Fragment? = null,
                        mvpLayout: MVPlayout<P, V>? = null,
                        inState: Bundle? = null) {

        val args: Bundle = activity.intent.extras ?: Bundle();
        if ((fragment != null) && (fragment.arguments != null)){
            args.putAll(fragment.arguments);
        }
        if ((mvpLayout != null) && (mvpLayout.args != null)) {
            args.putAll(mvpLayout.args);
        }
        presenter.initialize(activity, args, inState);
    }

    fun attachVu(inflater: LayoutInflater,
                 activity: Activity,
                 parentView: ViewGroup? = null,
                 fragment: Fragment? = null) {

        vu = createVu(inflater,
                activity,
                fragment = fragment,
                parentView = parentView);

        presenter.attachVu(vu!!);
    }

    fun onStartUI() {
        presenter.onStartVu();
    }

    fun onResumeUI() {
        vu!!.onFocusedChanged(true);
        presenter.onResumeVu();
    }

    fun onPauseUI() {
        presenter.onPauseVu();
        vu!!.onFocusedChanged(false);
    }

    fun onStopUI() {
        presenter.onStopVu();
    }

    fun destroyVu() {
        presenter.detachVu()
        vu!!.onDestroy();
        vu = null;
    }

    fun destroyPresenter() {
        presenter.onDestroy();
    }

    fun savePresenterState(outState: Bundle) {
        presenter.onSaveState(outState);
    }

}