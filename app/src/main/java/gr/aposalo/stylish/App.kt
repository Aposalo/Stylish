package gr.aposalo.stylish

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale

@HiltAndroidApp
class App : Application() {


    override fun onCreate() {
        super.onCreate()
        application = this
        appContext = applicationContext
        val localeCode = Locale.getDefault().language
        deviceLanguage = localeCode
        versionName = BuildConfig.VERSION_NAME
        databaseURL = BuildConfig.BASE_URL
    }

    companion object {
        lateinit var application: Application
        lateinit var appContext: Context
        lateinit var deviceLanguage: String
        lateinit var versionName: String
        lateinit var databaseURL: String
    }
}