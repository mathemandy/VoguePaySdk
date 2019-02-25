package android.voguepay.ng.voguepaysdk2.view.card

import android.voguepay.ng.voguepaysdk2.LabelResponse
import android.voguepay.ng.voguepaysdk2.view.AbstractViewModel
import android.voguepay.ng.voguepaysdk2.repository.CardRepository
import android.voguepay.ng.voguepaysdk2.util.ext.with
import android.voguepay.ng.voguepaysdk2.util.rx.SchedulerProvider
import android.voguepay.ng.voguepaysdk2.view.SingleLiveEvent
import androidx.lifecycle.MutableLiveData

class CardViewModel(private val cardRepository: CardRepository,
        private val schedulerProvider: SchedulerProvider) : AbstractViewModel(){
    val searchEvent = SingleLiveEvent<SearchEvent>()
    val uiData = MutableLiveData<PaymentUIModel>()

    fun getPaymentLink (data : String ){
        launch {

            searchEvent.value = SearchEvent(isLoading = true)

            cardRepository.getPaymentHeader(data)
                .with(schedulerProvider)
                .subscribe({
                    searchEvent.postValue(SearchEvent(isSuccess = true))

                }, {err ->
                    searchEvent.postValue(SearchEvent(error = err))
                })


        }
    }

    fun getResponse () {

        launch {
            cardRepository.getResponse().with(schedulerProvider)
                .subscribe({ response ->
                    uiData.value = PaymentUIModel(response)
                }, {e ->
                    uiData.value = PaymentUIModel(error = e)
                })
        }

    }

}
data class PaymentUIModel(val response: LabelResponse = LabelResponse(), val error: Throwable? = null)
data class SearchEvent(val isLoading: Boolean = false, val isSuccess: Boolean = false, val error: Throwable? = null)