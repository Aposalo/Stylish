package gr.aposalo.stylish.domain.preferences

interface Preferences {

    suspend fun saveAccessToken(accessTokenValue: String?)

    suspend fun loadAccessToken(): String

    suspend fun clearDataStore()

    suspend fun hasAccessToken(): Boolean

    companion object {
        const val NO_VALUE = "NO_VALUE_SET"
        const val ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY"
    }

}