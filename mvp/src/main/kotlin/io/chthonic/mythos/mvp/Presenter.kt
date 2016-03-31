package io.chthonic.mythos.mvp

import android.app.Activity
import android.os.Bundle
import java.lang.ref.WeakReference

/**
 * Created by jhavatar on 3/3/2016.
 */
interface Presenter<V> where V : Vu {

    var _vuRef: WeakReference<V>?;
    var _activityRef: WeakReference<Activity>?;
    var attached: Boolean;

    fun attachVu(vu: V) {
        this._vuRef = WeakReference<V>(vu);
    }
    fun detachVu() {
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

    fun initialize(activity: Activity, args: Bundle, inState: Bundle?) {
        _activityRef = WeakReference<Activity>(activity);
    }

    fun onStartVu();

    fun onResumeVu();

    fun onPauseVu();

    fun onStopVu();

    fun onDestroy() {
        attached = false;
    }

    fun onSaveState(outState: Bundle);
}