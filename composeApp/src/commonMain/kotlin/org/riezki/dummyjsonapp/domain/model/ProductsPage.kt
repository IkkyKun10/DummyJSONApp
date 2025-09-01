package org.riezki.dummyjsonapp.domain.model

/**
 * @author riezky maisyar
 */

data class ProductSummary(
    val id: Int,
    val title: String,
    val price: Double,
)

data class ProductsPage(
    val products: List<ProductSummary>,
)