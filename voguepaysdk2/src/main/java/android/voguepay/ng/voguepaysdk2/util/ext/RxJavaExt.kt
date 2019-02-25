package android.voguepay.ng.voguepaysdk2.util.ext

import android.voguepay.ng.voguepaysdk2.util.rx.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Single

fun <T> Single<T>.with(schedulerProvider: SchedulerProvider): Single<T> = observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())

fun Completable.with(schedulerProvider: SchedulerProvider): Completable = observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())