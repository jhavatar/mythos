package io.chthonic.mythos.mvp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout

/**
 * Created by jhavatar on 3/12/2016.
 *
 * Implement a MVP pattern using a FrameLayout.
 * Presenter is linked from onAttachedToWindow() to onDetachedFromWindow().
 * Note, removal of Presenter from PresenterCache, which calls Presenter's onDestroy(), still requires implementation.
 * Vu is created in onAttachedToWindow() and destroyed in onDetachedFromWindow().
 * @param P type of Presenter.
 * @param V type of Vu.
 */
abstract class MVPLayout<P, V>: FrameLayout  where P : Presenter<V>, V : Vu {

    companion object {
        const val ARGS_KEY = "_args_key"
        const val MVP_STATE_KEY = "_mvp_state_key"
    }

    val mvpDispatcher: MVPDispatcher<P, V> by lazy {
        createMVPDispatcher()
    }
    var args: Bundle? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    /**
     * @return MVPDispatcher instance used to coordinate MVP pattern.
     */
    abstract protected fun createMVPDispatcher(): MVPDispatcher<P, V>

    override fun onRestoreInstanceState (inState: Parcelable) {
        super.onRestoreInstanceState(null)

        val inBundle: Bundle = inState as Bundle
        if (inBundle.containsKey(ARGS_KEY)) {
            args = inBundle.getBundle(ARGS_KEY)
        }
        if (inBundle.containsKey(MVP_STATE_KEY)) {
            val mvpState: Bundle = inBundle.getBundle(MVP_STATE_KEY)
            mvpDispatcher.restorePresenterState(mvpState)
        }
    }

    override fun onSaveInstanceState (): Parcelable {
        super.onSaveInstanceState()
        val outState = Bundle()

        val mvpStateNew = Bundle()
        mvpDispatcher.savePresenterState(mvpStateNew)
        if (mvpStateNew.size() > 0) {
            outState.putParcelable(MVP_STATE_KEY, mvpStateNew)
        }

        if (args != null) {
            outState.putParcelable(ARGS_KEY, args)
        }

        return outState
    }

    override fun onAttachedToWindow(){
        super.onAttachedToWindow()

        mvpDispatcher.createVu(LayoutInflater.from(this.context),
                activity = this.context as Activity,
                parentView = this)
        this.addView(mvpDispatcher.vu!!.rootView)

        mvpDispatcher.linkPresenter(if (args != null) args!! else Bundle())
    }


    override fun onDetachedFromWindow() {
        mvpDispatcher.unlinkPresenter()
        mvpDispatcher.presenterCache.remove()
        val vuRootView: View = mvpDispatcher.vu!!.rootView
        mvpDispatcher.destroyVu()
        this.removeView(vuRootView)

        super.onDetachedFromWindow()
    }
}