package android.voguepay.ng.voguepaysdk.di.modules.remote.retrofit

import android.voguepay.ng.voguepaysdk.di.scopes.VoguePayAppScope
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber


@Module
class InterceptorsModule {

    @Provides @VoguePayAppScope
    internal fun provideNetworkInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            message -> Timber.e(message)
    }).setLevel(
        HttpLoggingInterceptor.Level.BODY
    )

    @Provides @VoguePayAppScope
    internal fun provideStethoNetworkInterceptor() = StethoInterceptor()
}