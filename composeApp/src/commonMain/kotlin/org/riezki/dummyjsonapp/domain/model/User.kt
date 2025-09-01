package org.riezki.dummyjsonapp.domain.model

/**
 * @author riezky maisyar
 */

data class User(
    val id: Int? = null,
    val username: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val gender: String? = null,
    val image: String? = null,
)