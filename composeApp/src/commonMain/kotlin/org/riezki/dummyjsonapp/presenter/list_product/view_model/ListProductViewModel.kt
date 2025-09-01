package org.riezki.dummyjsonapp.presenter.list_product.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.riezki.dummyjsonapp.core.domain.onError
import org.riezki.dummyjsonapp.core.domain.onSuccess
import org.riezki.dummyjsonapp.domain.RemoteDataSource
import org.riezki.dummyjsonapp.presenter.event.AppEvent.ProductsEvent
import org.riezki.dummyjsonapp.presenter.list_product.state.ProductsState

/**
 * @author riezky maisyar
 */

class ListProductViewModel(
    private val remoteDataSource: RemoteDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(ProductsState())
    val state = _state
        .onStart {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            loadProducts()
        }
        .stateIn(
            viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            initialValue = _state.value
        )

    fun onEvent(event: ProductsEvent) {
        when(event) {
            is ProductsEvent.OnNextProducts -> {
                // Handle next products event
                _state.update {
                    it.copy(
                        skip = it.skip + 10,
                        isLoading = true
                    )
                }
                viewModelScope.launch {
                    loadProducts()
                }
            }
            is ProductsEvent.OnPreviousProducts -> {
                // Handle previous products event
                if (state.value.skip == 0) return

                _state.update {
                    it.copy(
                        skip = it.skip - 10,
                        isLoading = true
                    )
                }

                viewModelScope.launch {
                    loadProducts()
                }
            }
        }
    }

    private suspend fun loadProducts() {
        remoteDataSource
            .getProducts(
                limit = state.value.limit,
                skip = state.value.skip,
            )
            .onSuccess { response ->
                _state.update {
                    it.copy(
                        data = response,
                        isLoading = false
                    )
                }
                println("List Product Response: $response")
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
                println("List Product Error: $error")
            }
    }
}