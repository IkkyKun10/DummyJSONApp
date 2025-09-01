package org.riezki.dummyjsonapp.presenter.event

/**
 * @author riezky maisyar
 */

sealed interface AppEvent {

    sealed class LoginEvent: AppEvent {
        data class OnUsernameChange(val username: String) : LoginEvent()
        data class OnPasswordChange(val password: String) : LoginEvent()
        object OnLoginClick : LoginEvent()
    }

    sealed class ProductsEvent: AppEvent {
        object OnNextProducts: ProductsEvent()
        object OnPreviousProducts: ProductsEvent()
    }

    sealed class ProfileEvent: AppEvent {

    }
}