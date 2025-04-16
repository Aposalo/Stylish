package gr.aposalo.stylish.data.repositories

import gr.aposalo.stylish.common.Resource
import gr.aposalo.stylish.data.network.ApiCalls
import gr.aposalo.stylish.data.network.dto.Product
import gr.aposalo.stylish.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ProductRepositoryImpl(
    private val apiCalls: ApiCalls,
): ProductRepository {
    override suspend fun getProducts(): Flow<Resource<List<Product>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val products = apiCalls.getProducts()
                emit(Resource.Success(products))
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()!!
                emit(Resource.Error(errorBody, e.code()))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server!", 0))
            }
        }
    }

    override suspend fun getProductById(id: String): Flow<Resource<Product>> {
        return flow {
            emit(Resource.Loading())
            try {
                val product = apiCalls.getProductId(id)
                emit(Resource.Success(product))
                } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()!!
                emit(Resource.Error(errorBody, e.code()))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server!", 0))
            }
        }
    }

    override suspend fun addProduct(product: Product): Flow<Resource<Product>> {
        return flow {
            emit(Resource.Loading())
            try {
                val product = apiCalls.addProduct(product)
                emit(Resource.Success(product))
                } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()!!
                emit(Resource.Error(errorBody, e.code()))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server!", 0))
            }
        }
    }

    override suspend fun updateProduct(
        id: String,
        product: Product
    ): Flow<Resource<Product>> {
        return flow {
            emit(Resource.Loading())
            try {
                val product = apiCalls.updateProduct(id, product)
                emit(Resource.Success(product))
                }
            catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()!!
                emit(Resource.Error(errorBody, e.code()))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server!", 0))
            }
        }
    }

    override suspend fun deleteProduct(id: String): Flow<Resource<Product>> {
        return flow {
            emit(Resource.Loading())
            try {
                val product = apiCalls.deleteProduct(id)
                emit(Resource.Success(product))
                } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()!!
                emit(Resource.Error(errorBody, e.code()))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server!", 0))
            }
        }
    }
}