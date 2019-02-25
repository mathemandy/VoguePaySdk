package android.voguepay.ng.voguepaysdk;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;

import java.lang.annotation.*;

/**
 * Created by numb3rs on 2/20/18.
 */


@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}
