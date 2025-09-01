package org.riezki.dummyjsonapp.presenter.profile_user.state

import org.riezki.dummyjsonapp.domain.model.User

/**
 * @author riezky maisyar
 */

data class ProfileUserState(
    val user: User = User(),
    val isLoading: Boolean = false
)