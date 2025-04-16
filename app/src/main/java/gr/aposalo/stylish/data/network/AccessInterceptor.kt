package gr.aposalo.stylish.data.network

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import gr.aposalo.stylish.App
import gr.aposalo.stylish.MainActivity
import gr.aposalo.stylish.domain.preferences.Preferences
import gr.aposalo.stylish.domain.repositories.AuthRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AccessInterceptor @Inject constructor(
    private val preferences: Preferences,
) : Interceptor, AppCompatActivity() {

    private val _tag = "[AccessInterceptor]"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        Log.e(_tag, "${response.code} for ${response.request.url}")

        if (response.code != 200 && response.code != 201) {
            try {
                logout()
            } catch (ex: Exception) {
                Log.e(_tag, "Token refresh failed with exception: ${ex.message}")
                logout()
                ex.printStackTrace()
            }
        }

        return response
    }


    private fun logout(){
        Log.e("refresh", "logging out")
        lifecycleScope.launch {
            preferences.clearDataStore()
            val intent = Intent(App.appContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            App.appContext.startActivity(intent)
        }
    }
}


