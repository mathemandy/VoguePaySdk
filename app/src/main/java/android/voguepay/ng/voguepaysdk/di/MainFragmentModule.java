package android.voguepay.ng.voguepaysdk.di;


import android.voguepay.ng.voguepaysdk.Ui.MainFragment;
import android.voguepay.ng.voguepaysdk.ViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentModule {


    @ContributesAndroidInjector()
    abstract MainFragment mainFragment();

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
}
