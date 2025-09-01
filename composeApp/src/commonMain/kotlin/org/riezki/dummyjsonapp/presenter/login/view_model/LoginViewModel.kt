package org.riezki.dummyjsonapp.presenter.login.view_model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.riezki.dummyjsonapp.domain.PreferenceRepository
import org.riezki.dummyjsonapp.domain.RemoteDataSource
import org.riezki.dummyjsonapp.presenter.event.AppEvent.LoginEvent
import org.riezki.dummyjsonapp.presenter.login.state.LoginUiState

/**
 * @author riezky maisyar
 */

class LoginViewModel(
    private val remoteDataSource: RemoteDataSource,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.OnUsernameChange -> {
                _state.value = state.value.copy(username = event.username)
            }

            is LoginEvent.OnPasswordChange -> {
                _state.value = state.value.copy(password = event.password)
            }

            is LoginEvent.OnLoginClick -> {

            }
        }
    }
}