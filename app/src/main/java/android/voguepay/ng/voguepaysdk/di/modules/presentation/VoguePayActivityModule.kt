package android.voguepay.ng.voguepaysdk.di.modules.presentation

import android.voguepay.ng.voguepaysdk.di.scopes.PerActivity
import android.voguepay.ng.voguepaysdk.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class  VoguePayActivityModule {

    @PerActivity
    @ContributesAndroidInjector
    abstract  fun bindMainActivity(): MainActivity
}