package android.voguepay.ng.voguepaysdk.viewmodel;

import android.voguepay.ng.voguepaysdk.VoguePayApplication;
import android.voguepay.ng.voguepaysdk.ui.payment.Repository;
import android.voguepay.ng.voguepaysdk.WhiteLabel;
import android.voguepay.ng.voguepaysdk.injection.VoguePayComponent;
import android.voguepay.ng.voguepaysdk2.LabelResponse;
import android.voguepay.ng.voguepaysdk2.Payment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.reactivex.disposables.Disposable;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class MainFragmentViewModel extends AndroidViewModel implements VoguePayComponent.Injectable {

    Repository mRepository;
    private Disposable searchDisposable;



    @Inject
    public MainFragmentViewModel(Repository repository) {
        super(VoguePayApplication.getInstance());

        this.mRepository = repository;
    }


    public LiveData<String> getURLForWebView(Payment payment){
     return  mRepository.getLink(payment);
    }
    public LiveData<LabelResponse> getURLForWebView(WhiteLabel payment){
     return  mRepository.getLink(payment);
    }



    @Override
    public void inject(@NotNull VoguePayComponent v) {
        v.inject(this);
    }
}
