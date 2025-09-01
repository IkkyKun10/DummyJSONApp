package org.riezki.dummyjsonapp.core.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * @author riezky maisyar
 */

@Serializable
data class UserDto(
    val id: Int? = null,
    val username: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val gender: String? = null,
    val image: String? = null,
)