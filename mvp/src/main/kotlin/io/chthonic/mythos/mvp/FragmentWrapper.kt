package io.chthonic.mythos.mvp

import android.app.Activity
import android.content.Context
import android.os.Bundle

/**
 * Created by jhavatar on 4/18/16.
 *
 * Helps supporting both Fragment types (standard and support) by wrapping both in one interface which reveals most popular methods.
 */
class FragmentWrapper {
    val standard: android.app.Fragment?
    val support: android.support.v4.app.Fragment?

    constructor (fragment: android.app.Fragment) {
        standard = fragment;
        support = null;
    }

    constructor (fragment: android.support.v4.app.Fragment) {
        support = fragment;
        standard = null;
    }

    /**
     * Return true if wrapping instance of support Fragment, otherwise wrapping standard fragment.
     */
    fun isSupport(): Boolean {
        return (support != null);
    }

    fun getActivity(): Activity? {
        if (isSupport()) {
            return support!!.activity;
        } else {
            return standard!!.activity;
        }
    }

    fun getContext(): Context? {
        if (isSupport()) {
            return support!!.context;
        } else {
            return standard!!.context;
        }
    }

    fun isAdded(): Boolean {
        if (isSupport()) {
            return support!!.isAdded;
        } else {
            return standard!!.isAdded;
        }
    }

    fun getArguments(): Bundle? {
        if (isSupport()) {
            return support!!.arguments;
        } else {
            return standard!!.arguments;
        }
    }

}