package android.voguepay.ng.voguepaysdk.di;


import android.voguepay.ng.voguepaysdk.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = MainFragmentModule.class)
    abstract MainActivity mainActivity();


}
