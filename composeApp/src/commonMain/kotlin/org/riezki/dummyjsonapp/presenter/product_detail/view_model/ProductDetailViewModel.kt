package org.riezki.dummyjsonapp.presenter.product_detail.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.riezki.dummyjsonapp.app.navigation.Route
import org.riezki.dummyjsonapp.core.domain.onError
import org.riezki.dummyjsonapp.core.domain.onSuccess
import org.riezki.dummyjsonapp.domain.RemoteDataSource
import org.riezki.dummyjsonapp.presenter.product_detail.state.ProductDetailState

/**
 * @author riezky maisyar
 */

class ProductDetailViewModel(
    private val remoteDataSource: RemoteDataSource,
    private val saveStateHandle: SavedStateHandle
) : ViewModel() {

    val productId = saveStateHandle.toRoute<Route.DetailProduct>().productId

    private val _state = MutableStateFlow(ProductDetailState())
    val state = _state
        .onStart {
            loadProductDetail()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _state.value
        )

    private suspend fun loadProductDetail() {
        _state.update { it.copy(isLoading = true) }
        remoteDataSource
            .getProductDetail(productId)
            .onSuccess { productDetail ->
                _state.update {
                    it.copy(
                        product = productDetail,
                        isLoading = false
                    )
                }
                println("ProductDetailViewModel: Success => $productDetail")
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
                println("ProductDetailViewModel: Error => $error")
            }
    }
}