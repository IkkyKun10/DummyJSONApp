package org.riezki.dummyjsonapp.domain

import org.riezki.dummyjsonapp.core.data.remote.payload.LoginRequest
import org.riezki.dummyjsonapp.core.domain.AppResult
import org.riezki.dummyjsonapp.core.domain.DataError
import org.riezki.dummyjsonapp.domain.model.Login
import org.riezki.dummyjsonapp.domain.model.ProductDetail
import org.riezki.dummyjsonapp.domain.model.ProductsPage
import org.riezki.dummyjsonapp.domain.model.User


/**
 * @author riezky maisyar
 */

interface RemoteDataSource {
    suspend fun login(request: LoginRequest): AppResult<Login, DataError.Remote>
    suspend fun getUserDetail(accessToken: String): AppResult<User, DataError.Remote>
    suspend fun getProducts(limit: Int, skip: Int, select: String? = null): AppResult<ProductsPage, DataError.Remote>
    suspend fun getProductDetail(id: Int): AppResult<ProductDetail, DataError.Remote>
}