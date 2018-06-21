package karthik.com.commBank;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;

public class ApplicationLifecycleHandler implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    private boolean isInBackground = false;
    private MyApplication myApplication;

    public ApplicationLifecycleHandler(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    public boolean isInBackground() {
        return isInBackground;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        myApplication.setCurrentActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        myApplication.setCurrentActivity(activity);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        myApplication.setCurrentActivity(activity);
        isInBackground = false;

    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }

    @Override
    public void onLowMemory() {
    }

    @Override
    public void onTrimMemory(int i) {
        if (i == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            isInBackground = true;
        }
    }
}
