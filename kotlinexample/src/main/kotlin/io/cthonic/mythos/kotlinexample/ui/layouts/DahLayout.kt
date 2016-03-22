package io.cthonic.mythos.kotlinexample.ui.layouts

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import io.cthonic.mythos.kotlinexample.ui.presenters.DahPresenter
import io.cthonic.mythos.kotlinexample.ui.vus.FusVu
import io.cthonic.mythos.mvp.MVPDispatcher
import io.cthonic.mythos.mvp.MVPlayout

/**
 * Created by jhavatar on 3/12/2016.
 */
class DahLayout : MVPlayout<DahPresenter, FusVu> {

    override fun createMVPDispatcher(): MVPDispatcher<DahPresenter, FusVu> {
        return object: MVPDispatcher<DahPresenter, FusVu>() {

            override fun createPresenter(): DahPresenter {
                return DahPresenter();
            }

            override fun createVu(inflater: LayoutInflater, activity: Activity, fragment: Fragment?, parentView: ViewGroup?): FusVu {
                return FusVu(inflater, activity = activity, fragment = fragment, parentView = parentView);
            }

        };
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

}