package gr.aposalo.stylish.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import gr.aposalo.stylish.domain.preferences.Preferences
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore("MyPreferences")

class PreferencesImpl(
    private val context: Context
): Preferences {
    override suspend fun saveAccessToken(accessTokenValue: String?) {

        val accessTokenKey = stringPreferencesKey(Preferences.ACCESS_TOKEN_KEY)
        context.dataStore.edit { settings ->
            settings[accessTokenKey] =
                if (accessTokenValue.isNullOrEmpty()) Preferences.NO_VALUE else accessTokenValue
        }
    }

    override suspend fun loadAccessToken(): String {
        val accessTokenKey = stringPreferencesKey(Preferences.ACCESS_TOKEN_KEY)
        val preferences = context.dataStore.data.first()
        return preferences[accessTokenKey] ?: Preferences.NO_VALUE
    }

    override suspend fun clearDataStore() {
        context.dataStore.edit {
            it.clear()
        }
    }

    override suspend fun hasAccessToken(): Boolean {
        val token = loadAccessToken()
        return !listOf(Preferences.NO_VALUE, String()).contains(token)
    }
}