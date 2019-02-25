package android.voguepay.ng.voguepaysdk.injection;


import android.content.Context;
import android.voguepay.ng.voguepaysdk.AppController;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class VoguePayModule {
    private AppController mAppConntroller;

    public VoguePayModule(AppController mAppConntroller) {
        this.mAppConntroller = mAppConntroller;
    }

    @Provides
    Context provideApplicationnContext() {
        return  mAppConntroller;

    }

}
