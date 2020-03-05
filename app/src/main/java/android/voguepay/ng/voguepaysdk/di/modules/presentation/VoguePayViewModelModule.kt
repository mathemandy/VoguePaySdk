package android.voguepay.ng.voguepaysdk.di.modules.presentation

import android.voguepay.ng.voguepaysdk.base.BaseViewModel
import android.voguepay.ng.voguepaysdk.di.keys.VoguePayViewModelKey
import android.voguepay.ng.voguepaysdk.ui.payment.PaymentViewModel
import android.voguepay.ng.voguepaysdk.viewmodel.MainFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract  class VoguePayViewModelModule  {



    /**
     * Fragment view models
     */

    @Binds
    @IntoMap
    @VoguePayViewModelKey(MainFragmentViewModel::class)
    abstract  fun bindMainFragmentViewModel(
        viewModel  : MainFragmentViewModel
    ): BaseViewModel

 @Binds
    @IntoMap
    @VoguePayViewModelKey(PaymentViewModel::class)
    abstract  fun bindPaymentViewModel(
        viewModel  : PaymentViewModel
    ): BaseViewModel


}