package android.voguepay.ng.voguepaysdk.injection;

import android.app.Application;
import android.voguepay.ng.voguepaysdk.AppController;
import android.voguepay.ng.voguepaysdk.AppModule;
import android.voguepay.ng.voguepaysdk.di.ActivityBindingModule;
import android.voguepay.ng.voguepaysdk.di.NetworkModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

import javax.inject.Singleton;


@Component(modules = {AppModule.class,
        AndroidSupportInjectionModule.class, NetworkModule.class,
ActivityBindingModule.class})
@Singleton
public interface AppComponent extends AndroidInjector<AppController> {

    @Override
    void inject(AppController instance);

    @Component.Builder
    interface Builder{
        @BindsInstance
        AppComponent.Builder application(Application application);
        AppComponent build();

    }

}
