package io.cthonic.mythos.mvp

import android.app.Activity
import android.os.Bundle
import java.lang.ref.WeakReference

/**
 * Created by jhavatar on 3/5/2016.
 */
open abstract class PresenterImpl<V> : Presenter<V> where V: Vu {

    override var _vuRef: WeakReference<V>? = null;
    override var _activityRef: WeakReference<Activity>? = null;

    override fun onStartVu() {
    }

    override fun onResumeVu() {
    }

    override fun onPauseVu() {
    }

    override fun onStopVu() {
    }

    override fun onDestroy() {
    }

    override fun onSaveState(outState: Bundle) {
    }

}