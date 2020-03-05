package android.voguepay.ng.voguepaysdk.di.modules.remote.retrofit

import android.content.Context
import android.voguepay.ng.voguepaysdk.di.scopes.VoguePayAppScope
import android.voguepay.ng.voguepaysdk.utils.constants.VoguePayNetworkConstants
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by ayokunlepaul on 28/10/2018.
 */
@Module(includes = [InterceptorsModule::class])
class OkhttpClientModule {

    @VoguePayAppScope @Provides
    internal fun provideFile(
        context: Context
    ): File = File(context.cacheDir, VoguePayNetworkConstants.VOGUE_OKHTTP_CACHE)

    @VoguePayAppScope @Provides
    internal fun provideCache(
        file: File
    ): Cache? = Cache(file, 10 * 1000 * 1000)

    @VoguePayAppScope @Provides
    internal fun provideOkhttpClient(
        httpInterceptor: HttpLoggingInterceptor,
        stethoInterceptor: StethoInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder().apply {
            followSslRedirects(false)
            followRedirects(false)
            retryOnConnectionFailure(true)
            addInterceptor(httpInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            addNetworkInterceptor(stethoInterceptor)
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }
        return builder.build()
    }
}