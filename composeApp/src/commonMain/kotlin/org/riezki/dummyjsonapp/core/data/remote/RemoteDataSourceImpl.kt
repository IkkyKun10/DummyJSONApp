package org.riezki.dummyjsonapp.core.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import org.riezki.dummyjsonapp.core.data.remote.dto.LoginDto
import org.riezki.dummyjsonapp.core.data.remote.dto.ProductDetailDto
import org.riezki.dummyjsonapp.core.data.remote.dto.ProductsPageDto
import org.riezki.dummyjsonapp.core.data.remote.dto.UserDto
import org.riezki.dummyjsonapp.core.data.remote.payload.LoginRequest
import org.riezki.dummyjsonapp.core.domain.AppResult
import org.riezki.dummyjsonapp.core.domain.DataError
import org.riezki.dummyjsonapp.core.domain.map
import org.riezki.dummyjsonapp.core.mapper.toDomain
import org.riezki.dummyjsonapp.core.utils.safeCall
import org.riezki.dummyjsonapp.domain.RemoteDataSource
import org.riezki.dummyjsonapp.domain.model.Login
import org.riezki.dummyjsonapp.domain.model.ProductDetail
import org.riezki.dummyjsonapp.domain.model.ProductsPage
import org.riezki.dummyjsonapp.domain.model.User


/**
 * @author riezky maisyar
 */

private const val BASE_URL = "https://dummyjson.com"

class RemoteDataSourceImpl(
    private val client: HttpClient,
) : RemoteDataSource {

    override suspend fun login(request: LoginRequest): AppResult<Login, DataError.Remote> {
        val response = client.post("$BASE_URL/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        return safeCall<LoginDto> { response }.map { dto ->
            dto.toDomain()
        }
    }

    override suspend fun getUserDetail(accessToken: String): AppResult<User, DataError.Remote> {
        val response = client.get("$BASE_URL/auth/me") {
            header(HttpHeaders.Authorization, "Bearer $accessToken")
        }

        return safeCall<UserDto> { response }.map { dto ->
            dto.toDomain()
        }
    }

    override suspend fun getProducts(limit: Int, skip: Int, select: String?): AppResult<ProductsPage, DataError.Remote> {
        val response = client.get("$BASE_URL/products") {
            parameter("limit", limit)
            parameter("skip", skip)
            if (!select.isNullOrBlank()) parameter("select", select)
        }
        return safeCall<ProductsPageDto> { response }.map { dto ->
            dto.toDomain()
        }
    }

    override suspend fun getProductDetail(id: Int): AppResult<ProductDetail, DataError.Remote> {
        val response = client.get("$BASE_URL/products/$id")
        return safeCall<ProductDetailDto> { response }.map { dto ->
            dto.toDomain()
        }
    }
}
