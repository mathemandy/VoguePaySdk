package android.voguepay.ng.voguepaysdk2.view

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Base ViewModel
 * - handle Rx jobs with launch() and clear them on onCleared
 */
abstract  class AbstractViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    fun launch (job : () -> Disposable){
        disposable.add(job())
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}