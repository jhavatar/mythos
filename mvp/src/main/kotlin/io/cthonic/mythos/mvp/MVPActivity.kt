package io.cthonic.mythos.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup

/**
 * Created by jhavatar on 3/5/2016.
 */
abstract class MVPActivity<P, V>: AppCompatActivity() where P : Presenter<V>, V : Vu {
    val mvpDispatcher: MVPDispatcher<P, V> by lazy {
        createMVPDispatcher();
    }

    abstract protected val contentViewRes: Int;
    abstract protected val mvpContainerResId: Int;

    private var _mvpContainer: ViewGroup? = null;
    var mvpContainer: ViewGroup?
        get() {
            if (_mvpContainer == null) {
                _mvpContainer = this.findViewById(mvpContainerResId) as? ViewGroup;
            }
            return _mvpContainer;
        }
        set(value) {
            _mvpContainer = value
        }

    abstract fun createMVPDispatcher(): MVPDispatcher<P, V>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(contentViewRes);
        mvpDispatcher.attachPresenter(this,
                inState = savedInstanceState);
        mvpDispatcher.attachVu(layoutInflater,
                activity = this,
                parentView = mvpContainer);

        mvpContainer!!.addView(mvpDispatcher.vu!!.rootView);
    }

    override fun onStart() {
        super.onStart();
        mvpDispatcher.onStartUI();
    }

    override fun onResume() {
        super.onResume();
        mvpDispatcher.onResumeUI();
    }

    override fun onPause() {
        mvpDispatcher.onPauseUI();
        super.onPause();
    }

    override fun onStop() {
        mvpDispatcher.onStopUI();
        super.onStop();
    }

    override fun onDestroy() {
        mvpDispatcher.destroyVu();
        mvpDispatcher.destroyPresenter();
        super.onDestroy();
    }


    override fun onSaveInstanceState(outState: Bundle){
        super.onSaveInstanceState(outState);
        mvpDispatcher.savePresenterState(outState);
    }

}