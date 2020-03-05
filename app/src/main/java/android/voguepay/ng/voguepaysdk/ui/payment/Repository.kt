package android.voguepay.ng.voguepaysdk.ui.payment

import android.util.Log
import android.voguepay.ng.voguepaysdk.WhiteLabel
import android.voguepay.ng.voguepaysdk.api.VoguePayApiService
import android.voguepay.ng.voguepaysdk2.LabelResponse
import android.voguepay.ng.voguepaysdk2.Payment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class Repository @Inject constructor(
    retrofit: Retrofit) {

    private  val apiService   = retrofit.create(VoguePayApiService::class.java)


    private var searchDisposable: Disposable? = null
    internal var authUrl: String? = null
    fun getLink(payment: Payment?): MutableLiveData<String> {
        if (searchDisposable != null) {
            searchDisposable!!.dispose()
        }
        val url = MutableLiveData<String>()
        apiService!!.getPaymentUrl(
            payment?.p,
            payment?.v_merchant_id,
            payment?.total,
            payment?.memo,
            payment?.cur
        )
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    searchDisposable = d
                }

                override fun onNext(s: String) {
                    url.value = s
                }

                override fun onError(e: Throwable) {
                    Log.e("Rep", e.message)
                }

                override fun onComplete() {
                    Log.e("TAG", "onComplete was reached")
                }
            }
            )
        return url
    }

    fun getLink(payment: WhiteLabel?): LiveData<LabelResponse?> {
        if (searchDisposable != null) {
            searchDisposable!!.dispose()
        }
        val gson = GsonBuilder().create()
        val payload = gson.toJson(payment)
        Log.e("repository", payload)
        val _url = MutableLiveData<LabelResponse?>()
        val url   = _url as LiveData<LabelResponse?>

        searchDisposable = apiService!!.getPaymentHeader(payload)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                Log.e("Rep", it.message)
            }
            .doOnComplete {
                Log.e("TAG", "onComplete was reached")

            }
            .subscribe{

                _url.postValue(it)
                if (it != null) {
                    Log.e("Rep", it.toString())
                }

            }

        return url
    }

}