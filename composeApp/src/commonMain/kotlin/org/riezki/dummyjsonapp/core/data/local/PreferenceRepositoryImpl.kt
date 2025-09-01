package org.riezki.dummyjsonapp.core.data.local

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.toFlowSettings
import kotlinx.coroutines.flow.Flow
import org.riezki.dummyjsonapp.domain.PreferenceRepository

/**
 * @author riezky maisyar
 */


@OptIn(ExperimentalSettingsApi::class)
class PreferenceRepositoryImpl(
    private val settings: Settings
) : PreferenceRepository {

    private val flowSettings = (settings as ObservableSettings).toFlowSettings()

    override val isLoggedIn: Flow<Boolean>
        get() = flowSettings.getBooleanFlow(IS_LOGGEDIN, false)

    override val accessToken: Flow<String>
        get() = flowSettings.getStringFlow(ACCESS_TOKEN, "")

    override val refreshToken: Flow<String>
        get() = flowSettings.getStringFlow(REFRESH_TOKEN, "")

    override suspend fun saveAccessToken(token: String) {
        flowSettings.putString(
            key = ACCESS_TOKEN,
            value = token
        )
    }

    override suspend fun saveRefreshToken(refreshToken: String) {
        flowSettings.putString(
            key = REFRESH_TOKEN,
            value = refreshToken
        )
    }

    override suspend fun saveLoggedIn(isLoggedIn: Boolean) {
        flowSettings.putBoolean(
            key = IS_LOGGEDIN,
            value = isLoggedIn
        )
    }

    companion object {
        const val IS_LOGGEDIN = "is_logged_in"
        const val ACCESS_TOKEN = "access_token"
        const val REFRESH_TOKEN = "refresh_token"
    }
}