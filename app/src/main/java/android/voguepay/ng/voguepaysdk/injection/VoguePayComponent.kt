package android.voguepay.ng.voguepaysdk.injection

import android.voguepay.ng.voguepaysdk.Ui.payment.PaymentViewModel
import android.voguepay.ng.voguepaysdk.viewmodel.MainFragmentViewModel
import dagger.Component

import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(VoguePayModule::class))
interface VoguePayComponent {

    fun inject(v: PaymentViewModel)
    fun inject (v: MainFragmentViewModel)


    interface Injectable {
        fun inject(v: VoguePayComponent)
    }
}