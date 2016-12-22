package io.chthonic.mythos.mvp

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.WindowId
import android.widget.FrameLayout

/**
 * Created by jhavatar on 3/12/2016.
 *
 * Implement a MVP pattern using a Framelayout.
 */
abstract class MVPLayout<P, V>: FrameLayout  where P : Presenter<V>, V : Vu {

    val mvpDispatcher: MVPDispatcher<P, V> by lazy {
        createMVPDispatcher();
    }
    var focusObserver: WindowId.FocusObserver? = null;
    var appLifecycleCallbacks: Application.ActivityLifecycleCallbacks? = null;

    var focused: Boolean = false;

    var args: Bundle? = null;
//    var presenterState: Bundle? = null;

    companion object {
        const val ARGS_KEY = "_args_key";
        const val MVP_STATE_KEY = "_mvp_state_key";
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    abstract protected fun createMVPDispatcher(): MVPDispatcher<P, V>;

    override fun onRestoreInstanceState (inState: Parcelable) {
        super.onRestoreInstanceState(null);

        val inBundle: Bundle = inState as Bundle;
        if (inBundle.containsKey(ARGS_KEY)) {
            args = inBundle.getBundle(ARGS_KEY);
        }
        if (inBundle.containsKey(MVP_STATE_KEY)) {
            val mvpState: Bundle = inBundle.getBundle(MVP_STATE_KEY);
            mvpDispatcher.restorePresenterState(mvpState);
        }
    }

    override fun onSaveInstanceState (): Parcelable {
        super.onSaveInstanceState();
        val outState: Bundle = Bundle();

        val mvpStateNew: Bundle = Bundle();
        mvpDispatcher.savePresenterState(mvpStateNew);
        if (mvpStateNew.size() > 0) {
            outState.putParcelable(MVP_STATE_KEY, mvpStateNew);

        }

        if (args != null) {
            outState.putParcelable(ARGS_KEY, args);
        }

        return outState;
    }

    private fun onFocusUI() {
        focused = true;
    }

    private fun onUnFocusUI() {
        focused = false;
    }

    override fun onAttachedToWindow(){
        super.onAttachedToWindow();

        mvpDispatcher.attachVu(LayoutInflater.from(this.context),
                activity = this.context as Activity,
                parentView = this);
        this.addView(mvpDispatcher.vu!!.rootView);

        mvpDispatcher.linkPresenter(if (args != null) args!! else Bundle());

        focusObserver = object:  WindowId.FocusObserver(){
            override fun onFocusLost(p0: WindowId?) {

                if (focused) {
                    onUnFocusUI();
                }
            }

            override fun onFocusGained(p0: WindowId?) {

                if (!focused) {
                    onFocusUI();
                }
            }

        };
        this.windowId.registerFocusObserver(focusObserver);
    }


    override fun onDetachedFromWindow() {
        if (focused) {
            onUnFocusUI();
        }

        this.windowId.unregisterFocusObserver(focusObserver);
        focusObserver = null;

        this.removeView(mvpDispatcher.vu!!.rootView);
        mvpDispatcher.detachVuAndUnlinkPresenter();
        mvpDispatcher.presenterCache.remove();

        (this.context?.applicationContext as? Application)?.unregisterActivityLifecycleCallbacks(appLifecycleCallbacks)
        appLifecycleCallbacks = null;

        super.onDetachedFromWindow();
    }
}