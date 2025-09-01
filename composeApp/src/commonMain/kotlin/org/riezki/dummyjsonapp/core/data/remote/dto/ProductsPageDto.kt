package org.riezki.dummyjsonapp.core.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * @author riezky maisyar
 */

@Serializable
data class ProductSummaryDto(
    val id: Int? = null,
    val title: String? = null,
    val price: Double? = null,
    val images: List<String>? = null,
)

@Serializable
data class ProductsPageDto(
    val products: List<ProductSummaryDto>,
    val total: Int,
    val skip: Int,
    val limit: Int,
)