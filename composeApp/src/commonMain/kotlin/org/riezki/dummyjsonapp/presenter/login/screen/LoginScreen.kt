package org.riezki.dummyjsonapp.presenter.login.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.riezki.dummyjsonapp.presenter.event.AppEvent.LoginEvent
import org.riezki.dummyjsonapp.presenter.login.component.LoginForm
import org.riezki.dummyjsonapp.presenter.login.state.LoginUiState

/**
 * @author riezky maisyar
 */

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    state: LoginUiState,
    onEvent: (LoginEvent) -> Unit,
    isLoading: Boolean = false,
    errorText: String? = null,
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = "Login Page",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp)
                .padding(top = 48.dp),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold
        )

        LoginForm(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
                .imePadding(),
            state = state,
            onEvent = onEvent,
            isLoading = isLoading,
            errorText = errorText
        )
    }
}