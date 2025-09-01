package org.riezki.dummyjsonapp.presenter.product_detail.state

import org.riezki.dummyjsonapp.domain.model.ProductDetail

/**
 * @author riezky maisyar
 */

data class ProductDetailState(
    val product: ProductDetail = ProductDetail(),
    val isLoading: Boolean = false,
)