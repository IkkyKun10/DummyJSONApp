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
    data object Profile : Route

    @Serializable
    data class DetailProduct(val productId: Int) : Route
}