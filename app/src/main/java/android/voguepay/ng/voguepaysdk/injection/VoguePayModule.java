package android.voguepay.ng.voguepaysdk.injection;


import android.content.Context;
import android.voguepay.ng.voguepaysdk.VoguePayApplication;
import dagger.Module;
import dagger.Provides;

@Module
public class VoguePayModule {
    private VoguePayApplication mAppConntroller;

    public VoguePayModule(VoguePayApplication mAppConntroller) {
        this.mAppConntroller = mAppConntroller;
    }

    @Provides
    Context provideApplicationnContext() {
        return  mAppConntroller;

    }

}
