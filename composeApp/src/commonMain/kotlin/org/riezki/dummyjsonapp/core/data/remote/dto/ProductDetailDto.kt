package org.riezki.dummyjsonapp.core.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * @author riezky maisyar
 */

@Serializable
data class ProductDetailDto(
    val id: Int,
    val title: String,
    val description: String? = null,
    val category: String? = null,
    val price: Double,
    val discountPercentage: Double? = null,
    val rating: Double? = null,
    val stock: Int? = null,
    val tags: List<String>? = null,
    val brand: String? = null,
    val sku: String? = null,
    val weight: Int? = null,
    val dimensions: DimensionsDto? = null,
    val warrantyInformation: String? = null,
    val shippingInformation: String? = null,
    val availabilityStatus: String? = null,
    val reviews: List<ReviewDto>? = null,
    val returnPolicy: String? = null,
    val minimumOrderQuantity: Int? = null,
    val meta: MetaDto? = null,
    val thumbnail: String? = null,
    val images: List<String>? = null,
)

@Serializable
data class DimensionsDto(
    val width: Double? = null,
    val height: Double? = null,
    val depth: Double? = null,
)

@Serializable
data class ReviewDto(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String,
)

@Serializable
data class MetaDto(
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val barcode: String? = null,
    val qrCode: String? = null,
)