package org.riezki.dummyjsonapp.core.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * @author riezky maisyar
 */

@Serializable
data class ProductSummaryDto(
    val id: Int,
    val title: String,
    val price: Double,
)

@Serializable
data class ProductsPageDto(
    val products: List<ProductSummaryDto>,
    val total: Int,
    val skip: Int,
    val limit: Int,
)