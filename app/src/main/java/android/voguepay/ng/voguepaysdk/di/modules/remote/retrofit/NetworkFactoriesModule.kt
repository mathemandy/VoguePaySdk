package android.voguepay.ng.voguepaysdk.di.modules.remote.retrofit

import android.voguepay.ng.voguepaysdk.di.scopes.VoguePayAppScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkFactoriesModule {

    @Provides @VoguePayAppScope
    fun provideGsonConverterFactory(
        gson: Gson
    ): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides @VoguePayAppScope
    internal fun provideGson() = GsonBuilder().create()

    @Provides @VoguePayAppScope
    fun provideRxJavaAdapterFactory(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
}