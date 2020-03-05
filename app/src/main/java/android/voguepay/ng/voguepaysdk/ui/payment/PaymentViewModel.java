package android.voguepay.ng.voguepaysdk.ui.payment;

import android.voguepay.ng.voguepaysdk.VoguePayApplication;
import android.voguepay.ng.voguepaysdk.injection.VoguePayComponent;

import androidx.lifecycle.AndroidViewModel;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class PaymentViewModel extends AndroidViewModel implements VoguePayComponent.Injectable


{

    @Inject
    public PaymentViewModel() {
        super(VoguePayApplication.getInstance());
    }



    @Override
    public void inject(@NotNull VoguePayComponent v) {

        v.inject(this);
    }
}
