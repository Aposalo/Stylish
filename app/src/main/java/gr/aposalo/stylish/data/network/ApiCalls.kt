package gr.aposalo.stylish.data.network

import gr.aposalo.stylish.data.network.dto.Product
import gr.aposalo.stylish.data.network.dto.login.LoginRequest
import gr.aposalo.stylish.data.network.dto.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ApiCalls {

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @GET("products")
    suspend fun getProducts(
    ): List<Product>

    @GET("products/{id}")
    suspend fun getProductId(
        @Path("id") id: String
    ): Product

    @POST("products")
    suspend fun addProduct(
        @Body product: Product
    ): Product


    @PUT("products/{id}")
    suspend fun updateProduct(
        @Path("id") id: String,
        @Body product: Product
    ): Product

    @DELETE("products/{id}")
    suspend fun deleteProduct(
        @Path("id") id: String
    ): Product

}