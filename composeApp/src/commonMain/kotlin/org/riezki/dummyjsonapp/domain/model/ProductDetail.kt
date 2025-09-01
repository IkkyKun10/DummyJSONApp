package org.riezki.dummyjsonapp.domain.model

/**
 * @author riezky maisyar
 */

data class ProductDetail(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val category: String? = null,
    val price: Double? = null,
    val discountPercentage: Double? = null,
    val rating: Double? = null,
    val stock: Int? = null,
    val tags: List<String>? = null,
    val brand: String? = null,
    val sku: String? = null,
    val weight: Int? = null,
    val dimensions: Dimensions? = null,
    val warrantyInformation: String? = null,
    val shippingInformation: String? = null,
    val availabilityStatus: String? = null,
    val reviews: List<Review>? = null,
    val returnPolicy: String? = null,
    val minimumOrderQuantity: Int? = null,
    val meta: Meta? = null,
    val thumbnail: String? = null,
    val images: List<String>? = null,
)

data class Dimensions(
    val width: Double? = null,
    val height: Double? = null,
    val depth: Double? = null,
)

data class Review(
    val rating: Int? = null,
    val comment: String? = null,
    val date: String? = null,
    val reviewerName: String? = null,
    val reviewerEmail: String? = null,
)

data class Meta(
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val barcode: String? = null,
    val qrCode: String? = null,
)