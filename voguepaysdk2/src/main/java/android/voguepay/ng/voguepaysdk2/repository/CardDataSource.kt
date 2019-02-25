package android.voguepay.ng.voguepaysdk2.repository

import android.voguepay.ng.voguepaysdk2.LabelResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CardDataSource {

    @FormUrlEncoded
    @POST("/api")
    fun getPaymentHeader(@Field("json") whiteLabel: String): Single<LabelResponse>

}