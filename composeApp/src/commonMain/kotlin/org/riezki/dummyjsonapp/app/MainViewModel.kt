package org.riezki.dummyjsonapp.app

import androidx.lifecycle.ViewModel
import org.riezki.dummyjsonapp.domain.PreferenceRepository

/**
 * @author riezky maisyar
 */

class MainViewModel(
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    val isLoggedIn = preferenceRepository.isLoggedIn
    /*.stateIn(
        viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = false
    )*/
}