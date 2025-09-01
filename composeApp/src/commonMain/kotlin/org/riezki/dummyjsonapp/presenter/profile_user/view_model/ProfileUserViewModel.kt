package org.riezki.dummyjsonapp.presenter.profile_user.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.riezki.dummyjsonapp.core.domain.onError
import org.riezki.dummyjsonapp.core.domain.onSuccess
import org.riezki.dummyjsonapp.domain.PreferenceRepository
import org.riezki.dummyjsonapp.domain.RemoteDataSource
import org.riezki.dummyjsonapp.presenter.profile_user.state.ProfileUserState

/**
 * @author riezky maisyar
 */

class ProfileUserViewModel(
    private val remoteDataSource: RemoteDataSource,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileUserState())
    val state = _state.asStateFlow()

    private suspend fun loadCurrentProfile() {
        _state.update { it.copy(isLoading = true) }
        remoteDataSource
            .getUserDetail(preferenceRepository.accessToken.first())
            .onSuccess { user ->
                _state.update {
                    it.copy(
                        user = user,
                        isLoading = false
                    )
                }
                println("ProfileUserViewModel Success => $user")
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
                println("ProfileUserViewModel Error => $error")
            }
    }

    init {
        viewModelScope.launch {
            loadCurrentProfile()
        }
    }
}