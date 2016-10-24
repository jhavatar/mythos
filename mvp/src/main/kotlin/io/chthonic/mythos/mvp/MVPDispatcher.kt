package io.chthonic.mythos.mvp

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by jhavatar on 3/9/2016.
 *
 * Coordinates the Presenter and View of a MVP architectural pattern and their lifecycle callbacks.
 */
abstract class MVPDispatcher<P, V>() where P : Presenter<V>, V : Vu {

    /** Reference to Vu instance */
    var vu: V? = null;

    /** Reference to Presenter instance */
    val presenter: P by lazy {
        createPresenter();
    }

    /**
     * Create Presenter instance.
     *
     * @return created instance.
     */
    protected abstract fun createPresenter(): P;

    /**
     * Create Vu instance.
     *
     * @param inflater a LayoutInflater to inflate layout XML
     * @param activity the Activity that MVP pattern belongs to.
     * @param fragment If present then the Fragment that Vu's rootView is a child of (Optional).
     * @param parentView If present then the ViewGroup that is the direct parent to Vu's rootView (Optional).
     * @return created instance.
     */
    protected abstract fun createVu(inflater: LayoutInflater,
                                    activity: Activity,
                                    fragment: FragmentWrapper? = null,
                                    parentView: ViewGroup? = null): V;

    /**
     * Attach Presenter to MVP pattern
     *
     * @param activity the Activity that MVP pattern belongs to.
     * @param fragment If present then the Fragment that MVP pattern belongs to (Optional).
     * @param mvpLayout If present then it is the MVPLayout MVP pattern belongs to (Optional).
     */
    fun attachPresenter(activity: Activity,
                        fragment: FragmentWrapper? = null,
                        mvpLayout: MVPLayout<P, V>? = null,
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


    /**
    * Attach Vu to MVP pattern and Presenter
    *
    * @param inflater a LayoutInflater to inflate layout XML.
    * @param activity the Activity that Vus' view belongs to.
    * @param fragment If present then the Fragment that Vu's rootView is a child of (Optional).
    * @param parentView If present then the ViewGroup that is the direct parent to Vu's rootView (Optional).
    */
    fun attachVu(inflater: LayoutInflater,
                 activity: Activity,
                 parentView: ViewGroup? = null,
                 fragment: FragmentWrapper? = null) {

        vu = createVu(inflater,
                activity,
                fragment = fragment,
                parentView = parentView);

        presenter.attachVu(vu!!);
    }

    /**
     * Called when Vu is to be displayed. Lifecycle callback.
     */
    fun startUI() {
        presenter.onStartVu();
    }

    /**
     * Called when Vu is moved to screen's foreground. Lifecycle callback.
     */
    fun resumeUI() {
        vu!!.onFocusedChanged(true);
        presenter.onResumeVu();
    }

    /**
     * Called when Vu is moved from screen's foreground to background. Lifecycle callback.
     */
    fun pauseUI() {
        presenter.onPauseVu();
        vu!!.onFocusedChanged(false);
    }

    /**
     * Called when Vu is no longer visible. Lifecycle callback.
     */
    fun stopUI() {
        presenter.onStopVu();
    }

    /**
     * Called when Vu object is detached from Presenter and being destroyed.
     */
    fun destroyVu() {
        presenter.detachVu()
        vu!!.onDestroy();
        vu = null;
    }

    /**
     * Called when presented object is being destroyed. Lifecycle callback.
     */
    fun destroyPresenter() {
        presenter.onDestroy();
    }

    /**
     * Called to allow saving of Presenter's data when presented object is about to be killed.
     *
     * @param outState Put Presenter's data to be saved in outState. Data will be available in initialize() call.
     */
    fun savePresenterState(outState: Bundle) {
        presenter.onSaveState(outState);
    }

}