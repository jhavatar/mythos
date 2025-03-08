package io.chthonic.mythos.kotlinexample.ui.vus

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.chthonic.mythos.kotlinexample.databinding.LayoutDahBinding
import io.chthonic.mythos.mvp.Vu

/**
 * Created by jhdev on 3/30/2016.
 */
class DahVu(
    layoutInflater: LayoutInflater,
    activity: Activity,
    fragment: Fragment? = null,
    parentView: ViewGroup? = null,
) : Vu<LayoutDahBinding>(
    layoutInflater,
    activity = activity,
    fragment = fragment,
    parentView = parentView,
) {

    fun setText(text: String) {
        binding.dahText.text = text
    }

    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        parentView: ViewGroup?,
    ): LayoutDahBinding =
        if (parentView != null) {
            LayoutDahBinding.inflate(layoutInflater, parentView, false)
        } else {
            LayoutDahBinding.inflate(layoutInflater)
        }
}