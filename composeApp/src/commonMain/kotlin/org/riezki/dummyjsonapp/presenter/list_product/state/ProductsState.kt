package org.riezki.dummyjsonapp.presenter.list_product.state

import org.riezki.dummyjsonapp.domain.model.ProductsPage

/**
 * @author riezky maisyar
 */

data class ProductsState(
    val data: ProductsPage = ProductsPage(emptyList()),
    val isLoading: Boolean = false,
    val skip: Int = 0,
    val limit: Int = 10,
)