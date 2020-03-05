package android.voguepay.ng.voguepaysdk.di.modules.presentation

import android.voguepay.ng.voguepaysdk.di.scopes.PerFragment
import android.voguepay.ng.voguepaysdk.ui.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract  class VoguePayFragmentModule {

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun bindMainFragment(): MainFragment

}