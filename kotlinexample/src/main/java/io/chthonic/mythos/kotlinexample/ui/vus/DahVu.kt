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
    inflater: LayoutInflater,
    activity: Activity,
    fragment: Fragment? = null,
    parentView: ViewGroup? = null,
) : Vu<LayoutDahBinding>(
    inflater,
    activity = activity,
    fragment = fragment,
    parentView = parentView,
) {

    fun setText(text: String) {
        binding.dahText.text = text
    }
}