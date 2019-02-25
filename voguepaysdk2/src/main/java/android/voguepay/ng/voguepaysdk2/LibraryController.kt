package android.voguepay.ng.voguepaysdk2

import android.app.Application
import android.voguepay.ng.voguepaysdk2.di.voguePayModules
import org.koin.android.ext.android.startKoin

class LibraryController : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, voguePayModules)
    }
}