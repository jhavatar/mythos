package io.chthonic.mythos.mvp

import android.app.Activity
import android.os.Bundle
import java.lang.ref.WeakReference

/**
 * Created by jhavatar on 3/3/2016.
 */
abstract class Presenter<V> where V : Vu {

    private var _vuRef: WeakReference<V>? = null;
    private var _activityRef: WeakReference<Activity>? = null;
    var attached: Boolean = true;

    open fun attachVu(vu: V) {
        this._vuRef = WeakReference<V>(vu);
    }
    open fun detachVu() {
        this._vuRef = null;
    }
    fun getVu(): V? {
        return _vuRef?.get();
    }
    fun hasVu(): Boolean {
        return (_vuRef != null) && (getVu() != null);
    }

    fun hasActivity(): Boolean {
        return (_activityRef != null) && (getActivity() != null);
    }
    fun getActivity(): Activity? {
        return _activityRef?.get();
    }

    open fun initialize(activity: Activity, args: Bundle, inState: Bundle?) {
        _activityRef = WeakReference<Activity>(activity);
    }

    open fun onStartVu() {

    }

    open fun onResumeVu() {

    }

    open fun onPauseVu() {

    }

    open fun onStopVu() {

    }

    open fun onDestroy() {
        attached = false;
    }

    open fun onSaveState(outState: Bundle) {

    }
}