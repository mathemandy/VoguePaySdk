package android.voguepay.ng.voguepaysdk.viewmodel

import android.voguepay.ng.voguepaysdk.WhiteLabel
import android.voguepay.ng.voguepaysdk.base.BaseViewModel
import android.voguepay.ng.voguepaysdk.ui.payment.Repository
import android.voguepay.ng.voguepaysdk2.LabelResponse
import android.voguepay.ng.voguepaysdk2.Payment
import androidx.lifecycle.LiveData
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(private val mRepository: Repository) :
    BaseViewModel() {

    private val searchDisposable: Disposable? = null

    fun getURLForWebView(payment: Payment?): LiveData<String> {
        return mRepository.getLink(payment)
    }

    fun getURLForWebView(payment: WhiteLabel?): LiveData<LabelResponse?> {
        return mRepository.getLink(payment)
    }


}