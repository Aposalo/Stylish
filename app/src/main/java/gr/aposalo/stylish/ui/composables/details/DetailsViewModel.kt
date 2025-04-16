package gr.aposalo.stylish.ui.composables.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.aposalo.stylish.common.Resource
import gr.aposalo.stylish.data.network.dto.Product
import gr.aposalo.stylish.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {


    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _product = MutableStateFlow<Product>(Product())
    val product = _product.asStateFlow()

    fun getProduct(id: String){
        viewModelScope.launch {
            productRepository.getProductById(id).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _isLoading.value = true
                    }
                    is Resource.Success -> {
                        _product.value = it.data!!
                        _isLoading.value = false
                    }
                    is Resource.Error -> {
                        _isLoading.value = false
                    }
                }

            }.launchIn(viewModelScope)
        }
    }

}