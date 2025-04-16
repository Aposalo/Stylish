package gr.aposalo.stylish.domain.repositories

import gr.aposalo.stylish.common.Resource
import gr.aposalo.stylish.data.network.dto.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(): Flow<Resource<List<Product>>>

    suspend fun getProductById(id: String): Flow<Resource<Product>>

    suspend fun addProduct(product: Product): Flow<Resource<Product>>

    suspend fun updateProduct(id: String, product: Product): Flow<Resource<Product>>

    suspend fun deleteProduct(id: String): Flow<Resource<Product>>
}