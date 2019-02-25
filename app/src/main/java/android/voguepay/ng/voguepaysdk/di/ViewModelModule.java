package android.voguepay.ng.voguepaysdk.di;


import android.voguepay.ng.voguepaysdk.ViewModelKey;
import android.voguepay.ng.voguepaysdk.viewmodel.MainFragmentViewModel;
import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel.class)
    abstract ViewModel bindNewsWebPageViewModel(MainFragmentViewModel mainFragmentViewModel);


}
