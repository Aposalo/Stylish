package gr.aposalo.stylish.data.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rating(
    @Json(name = "count") val count: String = "",
    @Json(name = "rate") val rate: String = ""
)