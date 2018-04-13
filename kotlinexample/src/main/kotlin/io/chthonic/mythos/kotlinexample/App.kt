package io.chthonic.mythos.kotlinexample

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import io.chthonic.mythos.mvp.MVPLifecycleCallbackManager

/**
 * Created by jhavatar on 12/10/2017.
 */
class App : android.app.Application() {

    companion object {
        lateinit var app: Application
            private set

        // ideally MVPLifecycleCallbackManager should be created and accessed through dependency injection
        val lifecycleManager: MVPLifecycleCallbackManager by lazy {
            MVPLifecycleCallbackManager()
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        LeakCanary.isInAnalyzerProcess(this)
    }
}