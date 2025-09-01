package org.riezki.dummyjsonapp.core.mapper

import org.riezki.dummyjsonapp.core.data.remote.dto.DimensionsDto
import org.riezki.dummyjsonapp.core.data.remote.dto.LoginDto
import org.riezki.dummyjsonapp.core.data.remote.dto.MetaDto
import org.riezki.dummyjsonapp.core.data.remote.dto.ProductDetailDto
import org.riezki.dummyjsonapp.core.data.remote.dto.ProductSummaryDto
import org.riezki.dummyjsonapp.core.data.remote.dto.ProductsPageDto
import org.riezki.dummyjsonapp.core.data.remote.dto.ReviewDto
import org.riezki.dummyjsonapp.core.data.remote.dto.UserDto
import org.riezki.dummyjsonapp.domain.model.Dimensions
import org.riezki.dummyjsonapp.domain.model.Login
import org.riezki.dummyjsonapp.domain.model.Meta
import org.riezki.dummyjsonapp.domain.model.ProductDetail
import org.riezki.dummyjsonapp.domain.model.ProductSummary
import org.riezki.dummyjsonapp.domain.model.ProductsPage
import org.riezki.dummyjsonapp.domain.model.Review
import org.riezki.dummyjsonapp.domain.model.User

/**
 * @author riezky maisyar
 */

fun ProductSummaryDto.toDomain() : ProductSummary =
    ProductSummary(
        id = this.id,
        title = this.title,
        price = this.price,
        images = this.images,
    )

fun ProductsPageDto.toDomain() =
    ProductsPage(
        products = this.products.map { it.toDomain() },
    )

fun LoginDto.toDomain() : Login =
    Login(
        id = id,
        username = username,
        email = email,
        firstName = firstName,
        lastName = lastName,
        gender = gender,
        image = image,
        accessToken = accessToken,
        refreshToken = refreshToken,
    )

fun UserDto.toDomain() =
    User(
        id = id,
        username = username,
        email = email,
        firstName = firstName,
        lastName = lastName,
        gender = gender,
        image = image,
    )

fun ProductDetailDto.toDomain() =
    ProductDetail(
        id = id,
        title = title,
        description = description,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        brand = brand,
        category = category,
        thumbnail = thumbnail,
        images = images,
        tags = tags,
        sku = sku,
        weight = weight,
        dimensions = dimensions?.toDomain(),
        warrantyInformation = warrantyInformation,
        shippingInformation = shippingInformation,
        availabilityStatus = availabilityStatus,
        reviews = reviews?.map { it.toDomain() },
        returnPolicy = returnPolicy,
        minimumOrderQuantity = minimumOrderQuantity,
        meta = meta?.toDomain(),
    )

fun DimensionsDto.toDomain() = Dimensions(
    width = width,
    height = height,
    depth = depth,
)

fun ReviewDto.toDomain() = Review(
    rating = rating,
    comment = comment,
    date = date,
    reviewerName = reviewerName,
    reviewerEmail = reviewerEmail,
)

fun MetaDto.toDomain() =
    Meta(
        createdAt = createdAt,
        updatedAt = updatedAt,
        barcode = barcode,
        qrCode = qrCode,
    )