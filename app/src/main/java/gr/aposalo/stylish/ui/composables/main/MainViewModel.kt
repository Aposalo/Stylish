package gr.aposalo.stylish.ui.composables.main

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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _showSearchScreen = MutableStateFlow(false)
    val showSearchScreen = _showSearchScreen.asStateFlow()

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _filteredProducts = MutableStateFlow<List<Product>>(emptyList())
    val filteredProducts = _filteredProducts.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()


    private val _titles = MutableStateFlow<List<String>>(emptyList())
    val titles = _titles.asStateFlow()

    init{

        // This should have been i call, i just do it as a mockup

        _titles.update {
            listOf(
                "Electronics",
                "Jewelery",
                "Men's Clothing",
                "Women's Clothing",
                "Gifts"
            )
        }

        getProducts()
    }

    fun getProducts(){
        viewModelScope.launch {
            productRepository.getProducts().onEach {
                when (it) {
                    is Resource.Loading -> {
                        _isLoading.value = true
                    }
                    is Resource.Success -> {
                        _products.value = it.data!!
                        applyFilters()
                        _isLoading.value = false
                    }
                    is Resource.Error -> {
                        _isLoading.value = false
                    }
                }

            }.launchIn(viewModelScope)
        }
    }

    private fun applyFilters() {
        _filteredProducts.value = _products.value.filter { product ->
            val matchesSearch = _searchText.value.isEmpty() || listOf(
                product.title,
            ).any { it.contains(_searchText.value, ignoreCase = true) }

            matchesSearch
        }
    }


}