package io.chthonic.mythos.mvp

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
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

        val lOG_TAG: String by lazy {
            MVPLayout::class.java.simpleName
        }
    }

    val mvpDispatcher: MVPDispatcher<P, V> by lazy {
        createMVPDispatcher()
    }
    var args: Bundle? = null

    @JvmOverloads
    constructor(context: Context?, lifecycleCallbackKeyAttrib: String? = null) : super(context) {
        this.lifecycleCallbackKeyAttr = lifecycleCallbackKeyAttrib
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttrs(context, attrs, defStyleAttr = defStyleAttr)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initAttrs(context, attrs, defStyleAttr = defStyleAttr, defStyleRes = defStyleRes)
    }


    /**
     * @return MVPDispatcher instance used to coordinate MVP pattern.
     */
    abstract protected fun createMVPDispatcher(): MVPDispatcher<P, V>

    protected var lifecycleCallbackKeyAttr: String? = null
    abstract protected fun registerLifecycleCallback()
    abstract protected fun unregisterLifecycleCallback()

    fun initAttrs(context: Context?, attrs: AttributeSet?, defStyleAttr: Int = 0, defStyleRes: Int = 0) {
        if (attrs != null) {
            val ta = context?.obtainStyledAttributes(
                    attrs,
                    R.styleable.MVPLayout,
                    defStyleAttr,
                    defStyleRes)

            lifecycleCallbackKeyAttr = ta?.getString(R.styleable.MVPLayout_mvplayout_callback_key) ?: lifecycleCallbackKeyAttr
            ta?.recycle()
        }
    }


    protected val lifecycleCallback: MVPLifecycleCallback by lazy {
        object:MVPLifecycleCallback {
            private var created: Boolean = false
            private var resumed: Boolean = false

            override fun onCreated(savedInstance: Bundle?) {
                Log.d(lOG_TAG, "onCreated, created = $created")
                if (!created) {
                    created = true

                    mvpDispatcher.createVu(LayoutInflater.from(context),
                            activity = context as Activity,
                            parentView = this@MVPLayout)
                    addView(mvpDispatcher.vu!!.rootView)
                }
            }

            override fun onResumed() {
                Log.d(lOG_TAG, "onResumed, resumed = $resumed")
                if (!resumed) {
                    resumed = true

                    mvpDispatcher.linkPresenter(if (args != null) args!! else Bundle())
                }
            }

            override fun onPaused() {
                Log.d(lOG_TAG, "onPaused, resumed = $resumed")
                if (resumed) {
                    resumed = false

                    mvpDispatcher.unlinkPresenter()
                }
            }


            override fun onDestroyed() {
                Log.d(lOG_TAG, "onDestroyed, created = $created")
                if (created) {
                    created = false

                    mvpDispatcher.presenterCache.remove()
                    val vuRootView = mvpDispatcher.vu?.rootView
                    mvpDispatcher.destroyVu()
                    if (vuRootView != null) {
                        removeView(vuRootView)
                    }

                    unregisterLifecycleCallback()
                }
            }

            override fun onSaveInstanceState(outState: Bundle?) {
                // Do not need lifecycle callback for saving state
            }
        }
    }


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
        Log.d(lOG_TAG, "onAttachedToWindow")

        registerLifecycleCallback()
        lifecycleCallback.onCreated(if (args != null) args!! else Bundle())
        lifecycleCallback.onResumed()
    }


    override fun onDetachedFromWindow() {
        Log.d(lOG_TAG, "onDetachedFromWindow")
        lifecycleCallback.onPaused()
        lifecycleCallback.onDestroyed()

        super.onDetachedFromWindow()
    }
}