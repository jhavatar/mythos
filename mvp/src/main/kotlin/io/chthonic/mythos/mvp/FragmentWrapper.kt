package io.chthonic.mythos.mvp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View

/**
 * Created by jhavatar on 4/18/16.
 *
 * Helps supporting both Fragment types (standard and support) by wrapping both in one interface which reveals most popular methods.
 */
class FragmentWrapper private constructor(val standard: android.app.Fragment?, val support: android.support.v4.app.Fragment?){

    /**
     * @param fragment instance of standard fragment to wrap.
     * @constructor create wrapper for standard fragment.
     */
    constructor (fragment: android.app.Fragment): this(standard = fragment, support = null)

    /**
     * @param fragment instance of support fragment to wrap.
     * @constructor create wrapper for support fragment.
     */
    constructor (fragment: android.support.v4.app.Fragment): this(standard = null, support = fragment)

    val activity: Activity?
        get() {
            if (isSupport()) {
                return support!!.activity
            } else {
                return standard!!.activity
            }
        }

    val context: Context?
        get() {
            if (isSupport()) {
                return support!!.context

            } else {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    return standard!!.context
                } else {
                    return standard!!.activity
                }
            }
        }

    val arguments: Bundle?
        get() {
            if (isSupport()) {
                return support!!.arguments
            } else {
                return standard!!.arguments
            }
        }

    val view: View?
        get() {
            if (isSupport()) {
                return support!!.view
            } else {
                return standard!!.view
            }
        }

    /**
     * @return true if wrapping instance of support Fragment, otherwise wrapping standard fragment.
     */
    fun isSupport(): Boolean {
        return (support != null)
    }

    fun isAdded(): Boolean {
        if (isSupport()) {
            return support!!.isAdded
        } else {
            return standard!!.isAdded
        }
    }

    fun isDetached(): Boolean {
        if (isSupport()) {
            return support!!.isDetached
        } else {
            return standard!!.isDetached
        }
    }



}