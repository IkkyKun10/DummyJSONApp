package org.riezki.dummyjsonapp.presenter.login.state

import org.riezki.dummyjsonapp.domain.model.Login


/**
 * @author riezky maisyar
 */

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val dataLogin: Login? = null,
    val errorMsg: String? = null,
)
