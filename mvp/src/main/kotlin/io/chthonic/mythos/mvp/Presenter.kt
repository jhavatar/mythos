package io.chthonic.mythos.mvp

import android.app.Activity
import android.os.Bundle
import java.lang.ref.WeakReference

/**
 * Created by jhavatar on 3/3/2016.
 *
 * Manages the Presenter component of an MVP architectural pattern.
 */
abstract class Presenter<V> where V : Vu {

    /** Memory leak safe reference to attached Vu */
    private var _vuRef: WeakReference<V>? = null;

    /** Memory leak safe reference activity. */
    private var _activityRef: WeakReference<Activity>? = null;

    var firstAttach: Boolean = true;
        private set;



    /**
     * Return true if a Vu is attached.
     */
    fun isAttached(): Boolean {
        return (_vuRef != null) && (getVu() != null);
    }

    /**
     * Return attached Vu.
     */
    fun getVu(): V? {
        return if (isAttached()) _vuRef?.get() else  null;
    }



//    /** True if Presenter is attached */
//    var attached: Boolean = true;

    fun onVuLinked(vu: V, inState: Bundle) {
        this._vuRef = WeakReference<V>(vu);
    }

    fun onVuUnlinked() {
        this._vuRef = null;
        firstAttach = false;
    }

    /**
     * Called when presented object is being destroyed. Perform final Presenter cleanup. Lifecycle callback.
     */
    open fun onDestroy() {
    }

//    /**
//     * Attach Vu to Presenter.
//     *
//     * @param vu VU thatis being attached.
//     */
//    open fun attachVu(vu: V) {
//        this._vuRef = WeakReference<V>(vu);
//    }
//
//    /**
//     * Detach Vu from Presenter
//     */
//    open fun detachVu() {
//        this._vuRef = null;
//    }



//    /**
//     * Return true if the activity is still available.
//     */
//    fun hasActivity(): Boolean {
//        return (_activityRef != null) && (getActivity() != null);
//    }
//
//    /** Return activity. */
//    fun getActivity(): Activity? {
//        return _activityRef?.get();
//    }

//    /**
//     * Initialize Presenter. Call after passed parameters are initialized.
//     *
//     * @param activity Activity that the Presenter belongs to.
//     * @param args Arguments passed to presented object.
//     * @param inState Data of Presenter's instance last saved in onSaveState call.
//     */
//    open fun initialize(activity: Activity, args: Bundle, inState: Bundle?) {
//        _activityRef = WeakReference<Activity>(activity);
//    }

//    /**
//     * Called when Vu is to be displayed. Lifecycle callback.
//     */
//    open fun onStartVu() {
//
//    }
//
//    /**
//     * Called when Vu is moved to screen's foreground. Lifecycle callback.
//     */
//    open fun onResumeVu() {
//
//    }
//
//    /**
//     * Called when Vu is moved from screen's foreground to background. Lifecycle callback.
//     */
//    open fun onPauseVu() {
//
//    }
//
//    /**
//     * Called when Vu is no longer visible. Lifecycle callback.
//     */
//    open fun onStopVu() {
//
//    }



    /**
     * Called to allow saving of Presenter's data when presented object is about to be killed.
     *
     * @param outState Put Presenter's data to be saved in outState. Data will be available in initialize() call.
     */
    open fun onSaveState(outState: Bundle) {

    }
}