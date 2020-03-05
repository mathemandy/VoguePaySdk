package android.voguepay.ng.voguepaysdk.di.modules.global

import android.content.Context
import android.voguepay.ng.voguepaysdk.VoguePayApplication
import android.voguepay.ng.voguepaysdk.di.modules.presentation.VoguePayActivityModule
import android.voguepay.ng.voguepaysdk.di.modules.presentation.VoguePayFragmentModule
import android.voguepay.ng.voguepaysdk.di.modules.presentation.VoguePayViewModelModule
import android.voguepay.ng.voguepaysdk.di.modules.remote.VoguePayRemoteModule
import android.voguepay.ng.voguepaysdk.di.scopes.VoguePayAppScope
import dagger.Binds
import dagger.Module


@Module(
    includes = [
        VoguePayViewModelModule::class,
        VoguePayFragmentModule::class,
        VoguePayActivityModule::class,
        VoguePayRemoteModule::class
    ]
)
abstract class VoguePayAppModule {
    @Binds
    @VoguePayAppScope
    internal abstract fun bindVoguePayApplicationContext(application: VoguePayApplication): Context

//    @Binds
//    @VoguePayAppScope
//    internal  abstract  fun  bindVoguePayExecutionThread(
//        observation
//    )
}