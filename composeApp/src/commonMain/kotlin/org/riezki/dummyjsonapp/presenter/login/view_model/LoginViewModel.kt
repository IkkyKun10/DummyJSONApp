package org.riezki.dummyjsonapp.presenter.login.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.riezki.dummyjsonapp.core.data.remote.payload.LoginRequest
import org.riezki.dummyjsonapp.core.domain.onError
import org.riezki.dummyjsonapp.core.domain.onSuccess
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
                Napier.i("Username Changed => ${state.value.username}")
            }

            is LoginEvent.OnPasswordChange -> {
                _state.value = state.value.copy(password = event.password)
                Napier.i("Password Changed => ${state.value.password}")
            }

            is LoginEvent.OnLoginClick -> {
                Napier.i("Login Clicked")
                viewModelScope.launch { 
                    val request = LoginRequest(
                        username = state.value.username,
                        password = state.value.password,
                        expiresInMins = 60
                    )
                    requestLogin(request)
                }
            }
        }
    }

    private suspend fun requestLogin(request: LoginRequest) {
        _state.update { it.copy(isLoading = true) }
        remoteDataSource
            .login(request)
            .onSuccess { response ->
                println("Login Success => $response")
                _state.update { 
                    it.copy(
                        dataLogin = response,
                        isLoading = false,
                    )
                }
                saveToken(
                    response.accessToken ?: "", 
                    response.refreshToken ?: ""
                )
                preferenceRepository.saveLoggedIn(true)
            }
            .onError { error ->
                println("LoginViewModel: Login Error => $error")
                _state.update { 
                    it.copy(
                        errorMsg = error.name,
                        isLoading = false,
                    )
                }
            }
    }
    
    private fun saveToken(
        accessToken: String,
        refreshToken: String
    ) = viewModelScope.launch { 
        preferenceRepository.saveAccessToken(accessToken)
        preferenceRepository.saveRefreshToken(refreshToken)
    }
}