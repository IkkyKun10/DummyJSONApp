package org.riezki.dummyjsonapp.presenter.list_product.state

import org.riezki.dummyjsonapp.domain.model.ProductsPage

/**
 * @author riezky maisyar
 */

data class ProductsState(
    val data: ProductsPage = ProductsPage(emptyList()),
    val isLoading: Boolean = false,
    val nextKey: Int = 1,
    val previousKey: Int = 1,
)