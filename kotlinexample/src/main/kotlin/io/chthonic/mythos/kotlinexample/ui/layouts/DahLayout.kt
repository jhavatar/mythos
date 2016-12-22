package io.chthonic.mythos.kotlinexample.ui.layouts

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import io.chthonic.mythos.kotlinexample.ui.presenters.DahPresenter
import io.chthonic.mythos.kotlinexample.ui.vus.DahVu
import io.chthonic.mythos.mvp.FragmentWrapper
import io.chthonic.mythos.mvp.MVPDispatcher
import io.chthonic.mythos.mvp.MVPLayout
import io.chthonic.mythos.mvp.PresenterCacheBasic

/**
 * Created by jhavatar on 3/12/2016.
 */
class DahLayout : MVPLayout<DahPresenter, DahVu> {

    override fun createMVPDispatcher(): MVPDispatcher<DahPresenter, DahVu> {
        return object: MVPDispatcher<DahPresenter, DahVu>(PresenterCacheBasic<DahPresenter>(DahPresenter())) {
            override val uid = 33

            override fun createVu(inflater: LayoutInflater, activity: Activity, fragment: FragmentWrapper?, parentView: ViewGroup?): DahVu {
                return DahVu(inflater, activity = activity, fragment = fragment, parentView = parentView);
            }

        };
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

}