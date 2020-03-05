package android.voguepay.ng.voguepaysdk.di.components

import android.voguepay.ng.voguepaysdk.VoguePayApplication
import android.voguepay.ng.voguepaysdk.di.modules.global.VoguePayAppModule
import android.voguepay.ng.voguepaysdk.di.scopes.VoguePayAppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication


@Component(modules = [VoguePayAppModule::class, AndroidSupportInjectionModule::class, AndroidInjectionModule::class])
@VoguePayAppScope
interface VoguePayApplicationComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance fun bindVoguePayApplicationInstance(application: VoguePayApplication): Builder
        fun buildAppControllerComponent(): VoguePayApplicationComponent
    }

}