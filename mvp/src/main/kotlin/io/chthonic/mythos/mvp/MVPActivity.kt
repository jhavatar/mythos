package io.chthonic.mythos.mvp

import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v7.app.AppCompatActivity
import android.util.Log


/**
 * Created by jhavatar on 3/5/2016.
 *
 * Implement a MVP pattern using an Activity.
 */
abstract class MVPActivity<P, V>: AppCompatActivity() where P : Presenter<V>, V : Vu {
    val mvpDispatcher: MVPDispatcher<P, V> by lazy {
        createMVPDispatcher();
    }

    protected abstract fun createMVPDispatcher(): MVPDispatcher<P, V>;

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("mew", "MVPActivity.onCreate");
        super.onCreate(savedInstanceState);

        mvpDispatcher.restorePresenterState(savedInstanceState)
        mvpDispatcher.attachVu(this.layoutInflater, this);
        setContentView(mvpDispatcher.vu!!.rootView);

        supportLoaderManager.initLoader(mvpDispatcher.uid,
                null,
                mvpDispatcher.presenterCache as LoaderManager.LoaderCallbacks<P>);
    }

    override fun onStart() {
        Log.d("mew", "MVPActivity.onStart");
        super.onStart();
        mvpDispatcher.linkPresenter(this.intent.extras)
    }

    override fun onStop() {
        Log.d("mew", "MVPActivity.onStop");
        mvpDispatcher.unlinkPresenter();
        super.onStop();
    }

    override fun onDestroy() {
        mvpDispatcher.detachVu();
        super.onDestroy()
    }


    override fun onSaveInstanceState(outState: Bundle){
        super.onSaveInstanceState(outState);
        mvpDispatcher.savePresenterState(outState);
    }
}