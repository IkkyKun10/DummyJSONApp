package org.riezki.dummyjsonapp.presenter.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.riezki.dummyjsonapp.presenter.event.AppEvent.LoginEvent
import org.riezki.dummyjsonapp.presenter.login.state.LoginUiState

/**
 * @author riezky maisyar
 */

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    state: LoginUiState,
    onEvent: (LoginEvent) -> Unit,
    isLoading: Boolean = false,
    errorText: String? = null,
) {

    var passwordVisible by remember { mutableStateOf(false) }
    val canSubmit = state.username.isNotBlank() && state.password.isNotBlank() && !isLoading

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = state.username,
            onValueChange = {
                onEvent(LoginEvent.OnUsernameChange(it))
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Username") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.colors()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = {
                onEvent(LoginEvent.OnPasswordChange(it))
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                val desc = if (passwordVisible) "Hide password" else "Show password"
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = desc)
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults.colors(),
        )

        if (!errorText.isNullOrBlank()) {
            Spacer(Modifier.height(8.dp))
            Text(text = errorText)
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                onEvent(LoginEvent.OnLoginClick)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = canSubmit,
            contentPadding = PaddingValues(vertical = 14.dp),
            shape = MaterialTheme.shapes.small
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Text("Login")
            }
        }
    }
}

@Preview
@Composable
fun LoginFormPrev() {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(primary = Color.White)
    ) {
        LoginForm(
            state = LoginUiState(),
            onEvent = {},
        )
    }
}