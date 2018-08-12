package io.chthonic.mythos.kotlinexample

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import io.chthonic.mythos.kotlinexample.ui.activities.FusActivity
import io.chthonic.mythos.mvp.ActivityLifecycleDispatcher
import io.chthonic.mythos.mvp.MVPLifecycleCallbackManager

/**
 * Created by jhavatar on 12/10/2017.
 */
class App : Application() {

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

        // register lifecycle callbacks for activities
        val activityLifecycleDispatcher = ActivityLifecycleDispatcher(mapOf(Pair(FusActivity::class.java, resources.getString(R.string.fus_lifecycle_key))))
        lifecycleManager.registerDispatcher(activityLifecycleDispatcher)
        this.registerActivityLifecycleCallbacks(activityLifecycleDispatcher)
    }
}