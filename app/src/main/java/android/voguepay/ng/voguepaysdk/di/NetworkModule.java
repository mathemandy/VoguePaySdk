package android.voguepay.ng.voguepaysdk.di;


import android.voguepay.ng.voguepaysdk.api.VoguePayApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Module
public class NetworkModule {

    public static String BASE_URL = "https://voguepay.com/";


    @Provides
    public static OkHttpClient providesOkHttpClientBuilder() {
        Interceptor interceptor = chain -> {
            Request.Builder builder = chain.request().newBuilder();
            return chain.proceed(builder.build());

        };

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //adding this bit of code to test if caching with retrofit  will work

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);

        httpClient.addNetworkInterceptor(loggingInterceptor);
        //httpClient.cache(cache);
        return httpClient.readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS).build();

    }


    @Provides
    public static VoguePayApiService provideWebService() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(providesOkHttpClientBuilder());

        Retrofit retrofit = builder.build();
        return retrofit.create(VoguePayApiService.class);

    }
}