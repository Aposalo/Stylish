package gr.aposalo.stylish.uc.product

import gr.aposalo.stylish.data.network.dto.Product
import gr.aposalo.stylish.domain.repositories.ProductRepository
import javax.inject.Inject

class ProductUC @Inject constructor(
    private val productRepository: ProductRepository
){
    suspend fun getProducts() = productRepository.getProducts()

    suspend fun getProductById(id: String) = productRepository.getProductById(id)

    suspend fun addProduct(product: Product) = productRepository.addProduct(product)

    suspend fun updateProduct(id: String, product: Product) = productRepository.updateProduct(id, product)

    suspend fun deleteProduct(id: String) = productRepository.deleteProduct(id)

}