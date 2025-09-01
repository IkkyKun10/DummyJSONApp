package org.riezki.dummyjsonapp.domain

import kotlinx.coroutines.flow.Flow

/**
 * @author riezky maisyar
 */

interface PreferenceRepository {
    val isLoggedIn: Flow<Boolean>
    val accessToken: Flow<String>
    val refreshToken: Flow<String>

    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(refreshToken: String)
    suspend fun saveLoggedIn(isLoggedIn: Boolean)
}