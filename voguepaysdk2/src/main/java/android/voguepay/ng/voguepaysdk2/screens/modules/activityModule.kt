package android.voguepay.ng.voguepaysdk2.screens.modules

import android.voguepay.ng.voguepaysdk2.screens.VoguePayViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val activtyModule = module {

    viewModel{VoguePayViewModel()}
}