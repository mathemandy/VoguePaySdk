package android.voguepay.ng.voguepaysdk;

import android.app.Activity;
import android.content.Context;
import android.voguepay.ng.voguepaysdk.injection.AppComponent;
import android.voguepay.ng.voguepaysdk.injection.DaggerAppComponent;
import androidx.fragment.app.Fragment;
import androidx.multidex.MultiDexApplication;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

import javax.inject.Inject;


public class AppController extends MultiDexApplication implements HasActivityInjector {

    static AppController INSTANCE;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;




    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        getAppComponent().inject(this);

    }

    public AppComponent getAppComponent(){
        return DaggerAppComponent.builder().application(this)
                .build();

    }


    public static  synchronized  AppController getInstance(){
        return  INSTANCE;
    }

    public static Context getContextInstance(){
        return INSTANCE.getApplicationContext();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }


}
