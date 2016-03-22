package io.cthonic.mythos.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by jhavatar on 3/4/2016.
 */
abstract class MVPFragment<P, V> : Fragment() where P : Presenter<V>, V : Vu {

    val mvpDispatcher: MVPDispatcher<P, V> by lazy {
        createMVPDispatcher();
    }

    abstract fun createMVPDispatcher(): MVPDispatcher<P, V>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        mvpDispatcher.attachPresenter(activity = activity,
                fragment = this,
                inState = savedInstanceState);
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {

        mvpDispatcher.attachVu(inflater,
                activity = this.activity,
                fragment = this,
                parentView = container);
        return mvpDispatcher.vu!!.rootView;
    }

    override fun onStart() {
        mvpDispatcher.onStartUI();
        super.onStart();
    }

    override fun onResume() {
        mvpDispatcher.onResumeUI();
        super.onResume();
    }

    override fun onPause() {
        mvpDispatcher.onPauseUI();
        super.onPause();
    }

    override fun onStop() {
        mvpDispatcher.onStopUI();
        super.onStop();
    }

    override fun onDestroyView() {
        mvpDispatcher.destroyVu();
        super.onDestroyView();
    }

    override fun onDestroy() {
        mvpDispatcher.destroyPresenter();
        super.onDestroy();
    }

    override fun onSaveInstanceState (outState: Bundle) {
        super.onSaveInstanceState(outState);
        mvpDispatcher.savePresenterState(outState);
    }

}