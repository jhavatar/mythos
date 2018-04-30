package io.chthonic.mythos.javaexample;

import android.app.Activity;
import android.app.Application;

import java.util.HashMap;
import java.util.Map;

import io.chthonic.mythos.javaexample.ui.activities.FusActivity;
import io.chthonic.mythos.mvp.ActivityLifecycleDispatcher;
import io.chthonic.mythos.mvp.MVPLifecycleCallbackManager;

public class App extends Application {
    public static final MVPLifecycleCallbackManager lifecycleManager = new MVPLifecycleCallbackManager();

    @Override
    public void onCreate() {
        super.onCreate();

        // register lifecycle callbacks for activities
        Map<Class<? extends Activity>, String> activityKeyMap = new HashMap<>();
        activityKeyMap.put(FusActivity.class, getResources().getString(R.string.fus_lifecycle_key));
        ActivityLifecycleDispatcher activityLifecycleDispatcher = new ActivityLifecycleDispatcher(activityKeyMap);
        lifecycleManager.registerDispatcher(activityLifecycleDispatcher);
        this.registerActivityLifecycleCallbacks(activityLifecycleDispatcher);
    }
}
