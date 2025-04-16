package gr.aposalo.stylish.ui.composables.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.aposalo.stylish.domain.preferences.Preferences
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val preferences: Preferences
): ViewModel()  {

    suspend fun hasAccessToken() = preferences.hasAccessToken()
}