package io.chthonic.mythos.kotlinexample

import android.app.Application
import com.squareup.leakcanary.LeakCanary

/**
 * Created by jhavatar on 12/10/2017.
 */
class App : android.app.Application() {

    companion object {
        lateinit var app: Application
            private set
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        LeakCanary.isInAnalyzerProcess(this)
    }
}