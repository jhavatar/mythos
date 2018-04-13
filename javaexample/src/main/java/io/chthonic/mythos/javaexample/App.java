package io.chthonic.mythos.javaexample;

import android.app.Application;

import io.chthonic.mythos.mvp.MVPLifecycleCallbackManager;

public class App extends Application {
    public static MVPLifecycleCallbackManager lifecycleManager = new MVPLifecycleCallbackManager();
}
