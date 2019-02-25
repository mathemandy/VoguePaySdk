package android.voguepay.ng.voguepaysdk.Ui.payment;

import android.util.Log;
import android.voguepay.ng.voguepaysdk.AppExecutors;
import android.voguepay.ng.voguepaysdk.WhiteLabel;
import android.voguepay.ng.voguepaysdk.api.VoguePayApiService;
import android.voguepay.ng.voguepaysdk2.LabelResponse;
import android.voguepay.ng.voguepaysdk2.Payment;
import androidx.lifecycle.MutableLiveData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

public class Repository {

    @Inject
    VoguePayApiService  apiService;

    private Disposable searchDisposable;

    String authUrl;



    AppExecutors appExecutors;

    @Inject
    public Repository(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }


    public MutableLiveData<String> getLink (Payment payment){
        if (searchDisposable != null){
            searchDisposable.dispose();
        }

        MutableLiveData<String> url = new MutableLiveData<>();
         apiService.getPaymentUrl(payment.getP(),
                 payment.getV_merchant_id(),
                 payment.getTotal(),
                 payment.getMemo(),
                 payment.getCur())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                               @Override
                               public void onSubscribe(Disposable d) {
                                   searchDisposable = d;
                               }

                               @Override
                               public void onNext(String s) {
                                   url.setValue(s);

                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.e("Rep", e.getMessage());

                               }

                               @Override
                               public void onComplete() {
                                   Log.e("TAG", "onComplete was reached");

                               }
                           }

                );

         return  url;


    }
    public MutableLiveData<LabelResponse> getLink (WhiteLabel payment){


        if (searchDisposable != null){
            searchDisposable.dispose();
        }
        Gson gson = new GsonBuilder().create();
        String payload = gson.toJson(payment);
        Log.e("repository", payload);


        MutableLiveData<LabelResponse> url = new MutableLiveData<>();
         apiService.getPaymentHeader(payload)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LabelResponse>() {
                               @Override
                               public void onSubscribe(Disposable d) {
                                   searchDisposable = d;
                               }

                               @Override
                               public void onNext(LabelResponse s) {
                                   url.setValue(s);
                                   if (s != null) {
                                       Log.e("Rep", s.toString());
                                   }
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.e("Rep", e.getMessage());

                               }

                               @Override
                               public void onComplete() {
                                   Log.e("TAG", "onComplete was reached");

                               }
                           }

                );

         return  url;


    }
}
