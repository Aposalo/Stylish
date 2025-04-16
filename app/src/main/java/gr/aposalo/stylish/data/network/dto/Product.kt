package gr.aposalo.stylish.data.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "category") val category: String = "",
    @Json(name = "description") val description: String = "",
    @Json(name = "id") val id: String = "",
    @Json(name = "image") val image: String = "",
    @Json(name = "price") val price: String = "",
    @Json(name = "rating") val rating: Rating = Rating(),
    @Json(name = "title") val title: String = ""
)