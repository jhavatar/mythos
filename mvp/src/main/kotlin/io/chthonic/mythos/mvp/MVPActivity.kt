package io.chthonic.mythos.mvp

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v7.app.AppCompatActivity
import android.support.v4.content.Loader
import android.view.LayoutInflater
import android.view.ViewGroup


/**
 * Created by jhavatar on 3/5/2016.
 *
 * Implement a MVP pattern using an Activity.
 */
abstract class MVPActivity<P, V>: AppCompatActivity(), LoaderManager.LoaderCallbacks<P> where P : Presenter<V>, V : Vu {
//    val mvpDispatcher: MVPDispatcher<P, V> by lazy {
//        createMVPDispatcher();
//    }

    var presenter : P? = null;
    var vu : V? = null;

    abstract fun createPresenter(): P;

    protected abstract fun createMVPDispatcher(): MVPDispatcher<P, V>;

    protected abstract fun createVu(inflater: LayoutInflater,
                                    activity: Activity,
                                    fragment: FragmentWrapper? = null,
                                    parentView: ViewGroup? = null): V;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

//        mvpDispatcher.attachPresenter(this,
//                inState = savedInstanceState);
//        mvpDispatcher.attachVu(layoutInflater,
//                activity = this);

        vu = createVu(this.layoutInflater, this);
        setContentView(vu!!.rootView);
    }

    override fun onStart() {
        super.onStart();

        presenter!!.onVuAttached(vu!!, this.intent.extras ?: Bundle());


//        mvpDispatcher.startUI();
    }

//    override fun onResume() {
//        super.onResume();
//        mvpDispatcher.resumeUI();
//    }
//
//    override fun onPause() {
//        mvpDispatcher.pauseUI();
//        super.onPause();
//    }

    override fun onStop() {
//        mvpDispatcher.stopUI();
        presenter!!.onVuDetached()
        super.onStop();
    }

//    override fun onDestroy() {
//        mvpDispatcher.destroyVu();
//        mvpDispatcher.destroyPresenter();
//        super.onDestroy();
//    }


    override fun onSaveInstanceState(outState: Bundle){
        super.onSaveInstanceState(outState);
        presenter!!.onSaveState(outState);
//        mvpDispatcher.savePresenterState(outState);
    }

    override fun onCreateLoader(id: Int, arg: Bundle): Loader<P> {
        return PresenterLoader<P>(this, {
            createPresenter()
        }) as (Loader<P>)
    }

    override fun onLoadFinished(loader: Loader<P>, presenter: P) {
        this.presenter = presenter;
//        mvpDispatcher.presenter = presenter
    }

    override fun onLoaderReset(loader: Loader<P>) {
//        presenter = null
    }

}