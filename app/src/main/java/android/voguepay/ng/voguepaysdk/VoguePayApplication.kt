package android.voguepay.ng.voguepaysdk

import android.content.Context
import android.voguepay.ng.voguepaysdk.di.components.DaggerVoguePayApplicationComponent
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class VoguePayApplication : DaggerApplication() {


    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        initTimber()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =  DaggerVoguePayApplicationComponent.builder().bindVoguePayApplicationInstance(this).buildAppControllerComponent()


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    private fun initTimber(){
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }


}