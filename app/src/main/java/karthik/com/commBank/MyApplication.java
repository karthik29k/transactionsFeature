package karthik.com.commBank;

import android.app.Activity;
import android.app.Application;

import karthik.com.commBank.utils.ConnectivityUtils;

public class MyApplication extends Application {

    private ApplicationLifecycleHandler applicationLifecycleHandler;
    private Activity currentActivity;

    private ConnectivityUtils connectivityUtilsInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationLifecycleHandler = new ApplicationLifecycleHandler(this);
        registerActivityLifecycleCallbacks(applicationLifecycleHandler);
        registerComponentCallbacks(applicationLifecycleHandler);
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity activity) {
        this.currentActivity = activity;
    }

    public ConnectivityUtils getConnectivityUtilsInstance() {
        if (connectivityUtilsInstance == null) {
            connectivityUtilsInstance = new ConnectivityUtils(this);
        }
        return connectivityUtilsInstance;
    }
}
