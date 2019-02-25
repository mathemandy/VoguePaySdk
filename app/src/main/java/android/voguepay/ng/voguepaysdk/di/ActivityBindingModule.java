package android.voguepay.ng.voguepaysdk.di;


import android.voguepay.ng.voguepaysdk.Ui.MainActivity;
import android.voguepay.ng.voguepaysdk.Ui.MainFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = MainFragmentModule.class)
    abstract MainActivity mainActivity();


}
