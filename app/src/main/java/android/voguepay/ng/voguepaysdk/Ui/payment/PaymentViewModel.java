package android.voguepay.ng.voguepaysdk.Ui.payment;

import android.app.Application;
import android.voguepay.ng.voguepaysdk.AppController;
import android.voguepay.ng.voguepaysdk.injection.VoguePayComponent;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class PaymentViewModel extends AndroidViewModel implements VoguePayComponent.Injectable


{

    @Inject
    public PaymentViewModel() {
        super(AppController.getInstance());
    }



    @Override
    public void inject(@NotNull VoguePayComponent v) {

        v.inject(this);
    }
}
