package org.riezki.dummyjsonapp.presenter.login.state


/**
 * @author riezky maisyar
 */

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false
)
