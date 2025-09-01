package org.riezki.dummyjsonapp.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.riezki.dummyjsonapp.app.navigation.Route
import org.riezki.dummyjsonapp.di.appModule
import org.riezki.dummyjsonapp.presenter.list_product.screen.ListProductsScreen
import org.riezki.dummyjsonapp.presenter.list_product.view_model.ListProductViewModel
import org.riezki.dummyjsonapp.presenter.login.screen.LoginScreen
import org.riezki.dummyjsonapp.presenter.login.view_model.LoginViewModel

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            modules(appModule)
        }
    ) {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Route.Login
        ) {
            composable<Route.Login> {
                val loginViewModel = koinInject<LoginViewModel>()

                val state by loginViewModel.state.collectAsStateWithLifecycle()

                LoginScreen(
                    modifier = Modifier
                        .fillMaxSize(),
                    state = state,
                    onEvent = loginViewModel::onEvent,
                    isLoading = state.isLoading
                )
            }

            composable<Route.Products> {
                val viewModel = koinInject<ListProductViewModel>()

                val state by viewModel.state.collectAsStateWithLifecycle()

                ListProductsScreen(
                    modifier = Modifier,
                    products = state.data.products,
                    onClick = {

                    },
                )
            }

            composable<Route.DetailProduct> {

            }

            composable<Route.Profile> {

            }
        }
    }
}