package org.riezki.dummyjsonapp.domain.model

/**
 * @author riezky maisyar
 */

data class ProductSummary(
    val id: Int? = null,
    val title: String? = null,
    val price: Double? = null,
    val images: List<String>? = null,
)

data class ProductsPage(
    val products: List<ProductSummary>,
)