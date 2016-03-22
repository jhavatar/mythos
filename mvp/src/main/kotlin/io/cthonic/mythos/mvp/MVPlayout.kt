package io.cthonic.mythos.mvp

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
 */
abstract class MVPlayout<P, V>: FrameLayout  where P : Presenter<V>, V : Vu {

    var mvpDispatcher: MVPDispatcher<P, V>? = null;
    var focusObserver: WindowId.FocusObserver? = null;
    var appLifecycleCallbacks: Application.ActivityLifecycleCallbacks? = null;

    var focused: Boolean = false;

    var args: Bundle? = null;
    var presenterState: Bundle? = null;

    companion object {
        const val ARGS_KEY = "_args_key";
        const val PRESENTER_KEY = "_presenter_key";
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
            args = inBundle.getParcelable(ARGS_KEY);
        }
        if (inBundle.containsKey(PRESENTER_KEY)) {
            presenterState = inBundle.getParcelable(PRESENTER_KEY);
        }
    }

    override fun onSaveInstanceState (): Parcelable {
        super.onSaveInstanceState();
        val outState: Bundle = Bundle();

        if (mvpDispatcher != null) {
            val presenterStateNew: Bundle = Bundle();
            mvpDispatcher!!.savePresenterState(presenterStateNew);
            if (presenterStateNew.keySet().size > 0) {
                this.presenterState = presenterStateNew;
                outState.putParcelable(PRESENTER_KEY, presenterStateNew);

            } else {
                this.presenterState = null;
            }
        }

        if (args != null) {
            outState.putParcelable(ARGS_KEY, args);
        }

        return outState;
    }

    private fun onFocusUI() {
        focused = true;
        mvpDispatcher!!.onResumeUI();
    }

    private fun onUnFocusUI() {
        focused = false;
        mvpDispatcher!!.onPauseUI();
    }

    override fun onAttachedToWindow(){
        super.onAttachedToWindow();

        mvpDispatcher = createMVPDispatcher();
        mvpDispatcher!!.attachPresenter(context as Activity, mvpLayout = this, inState = presenterState);
        mvpDispatcher!!.attachVu(LayoutInflater.from(this.context),
                activity = this.context as Activity,
                parentView = this);
        this.addView(mvpDispatcher!!.vu!!.rootView);

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

        this.removeView(mvpDispatcher!!.vu!!.rootView);
        mvpDispatcher!!.destroyVu();

        (this.context?.applicationContext as? Application)?.unregisterActivityLifecycleCallbacks(appLifecycleCallbacks)
        appLifecycleCallbacks = null;

        mvpDispatcher!!.destroyPresenter();

        mvpDispatcher = null;

        super.onDetachedFromWindow();
    }
}