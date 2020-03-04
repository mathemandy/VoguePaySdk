package android.voguepay.ng.voguepaysdk2.di

import android.voguepay.ng.voguepaysdk2.repository.CardRepository
import android.voguepay.ng.voguepaysdk2.repository.CardRepositoryImpl
import android.voguepay.ng.voguepaysdk2.screens.modules.activtyModule
import android.voguepay.ng.voguepaysdk2.util.rx.ApplicationScheduler
import android.voguepay.ng.voguepaysdk2.util.rx.SchedulerProvider
import android.voguepay.ng.voguepaysdk2.view.card.CardViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.sampleapp.di.remoteDatasourceModule
import org.koin.standalone.StandAloneContext.loadKoinModules


fun  injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        activtyModule,
        cardAppModule,
        rxModule,
        remoteDatasourceModule)
}


val cardAppModule = module {
    viewModel{CardViewModel(get(), get())}

    //card Data Repository
    single { CardRepositoryImpl(get()) as CardRepository }
}

val rxModule = module {
    single {ApplicationScheduler() as SchedulerProvider}

}

val voguePayModules = listOf(cardAppModule, rxModule)