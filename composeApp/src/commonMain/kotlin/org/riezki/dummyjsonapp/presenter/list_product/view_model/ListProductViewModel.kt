package org.riezki.dummyjsonapp.presenter.list_product.view_model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.riezki.dummyjsonapp.presenter.event.AppEvent.ProductsEvent
import org.riezki.dummyjsonapp.presenter.list_product.state.ProductsState

/**
 * @author riezky maisyar
 */

class ListProductViewModel : ViewModel() {

    private val _state = MutableStateFlow(ProductsState())
    val state = _state.asStateFlow()

    fun onEvent(event: ProductsEvent) {
        when(event) {
            is ProductsEvent.OnNextProducts -> {
                // Handle next products event
            }
            is ProductsEvent.OnPreviousProducts -> {
                // Handle previous products event
            }
        }
    }

    private fun loadProducts(page: Int) {

    }
}