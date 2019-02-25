package android.voguepay.ng.voguepaysdk2.repository

import android.voguepay.ng.voguepaysdk2.LabelResponse
import io.reactivex.Completable
import io.reactivex.Single

/*
* Card Repository
* */
interface  CardRepository {
    fun getPaymentHeader(data : String) : Completable
    fun getResponse(): Single<LabelResponse>
}

/*
* Card Repository
* Make usr of WeatherDataSource & add some cache
* */
class CardRepositoryImpl (private val cardDataSource: CardDataSource ) : CardRepository {
    var cardRespone : LabelResponse = LabelResponse()

    override fun getPaymentHeader(data: String): Completable = cardDataSource.getPaymentHeader(data)
        .doOnSuccess{cardRespone = it }
        .toCompletable()
    override fun getResponse(): Single<LabelResponse> = Single.just(cardRespone)
}
