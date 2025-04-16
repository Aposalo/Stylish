package gr.aposalo.stylish.data.network.dto.login

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(
    @Json(name = "password") val password: String,
    @Json(name = "username") val username: String
)