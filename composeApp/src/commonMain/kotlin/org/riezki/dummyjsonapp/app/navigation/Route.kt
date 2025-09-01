package org.riezki.dummyjsonapp.app.navigation

import kotlinx.serialization.Serializable

/**
 * @author riezky maisyar
 */

@Serializable
sealed interface Route {

    @Serializable
    data object Login: Route

    @Serializable
    data object Products: Route

    @Serializable
    data class Profile(val username: String) : Route

    @Serializable
    data class DetailProduct(val productId: Int) : Route
}