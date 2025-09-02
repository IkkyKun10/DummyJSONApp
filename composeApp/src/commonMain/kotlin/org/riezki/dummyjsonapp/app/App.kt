package org.riezki.dummyjsonapp.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.riezki.dummyjsonapp.app.components.DummyBottomBar
import org.riezki.dummyjsonapp.app.navigation.Route
import org.riezki.dummyjsonapp.di.appModule
import org.riezki.dummyjsonapp.di.platformModule
import org.riezki.dummyjsonapp.presenter.event.AppEvent
import org.riezki.dummyjsonapp.presenter.list_product.screen.ListProductsScreen
import org.riezki.dummyjsonapp.presenter.list_product.view_model.ListProductViewModel
import org.riezki.dummyjsonapp.presenter.login.screen.LoginScreen
import org.riezki.dummyjsonapp.presenter.login.view_model.LoginViewModel
import org.riezki.dummyjsonapp.presenter.product_detail.screen.ProductDetailScreenRoot
import org.riezki.dummyjsonapp.presenter.profile_user.screen.ProfileScreen
import org.riezki.dummyjsonapp.presenter.profile_user.view_model.ProfileUserViewModel

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            modules(appModule, platformModule)
        }
    ) {
        val navController = rememberNavController()
        val mainViewModel = koinViewModel<MainViewModel>()

        val isLoggedIn by mainViewModel.isLoggedIn.collectAsStateWithLifecycle()
//        val isLoggedIn = runBlocking { mainViewModel.isLoggedIn.first() }

        val backstack by navController.currentBackStackEntryAsState()
        val showBottomBar = isLoggedIn && when (backstack?.destination?.route) {
            Route.Products::class.qualifiedName -> true
            Route.Profile::class.qualifiedName -> true
            else -> false
        }

        Scaffold(
            bottomBar = { if (showBottomBar) DummyBottomBar(navController) }
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = if (isLoggedIn) Route.Products else Route.Login
            ) {
                composable<Route.Login> {
                    val loginViewModel = koinViewModel<LoginViewModel>()

                    val state by loginViewModel.state.collectAsStateWithLifecycle()

                    LaunchedEffect(state.dataLogin) {
                        if (state.dataLogin != null) {
                            navController.navigate(Route.Products) {
                                popUpTo(Route.Login) {
                                    inclusive = true
                                }
                            }
                        }
                    }

                    LoginScreen(
                        modifier = Modifier
                            .fillMaxSize(),
                        state = state,
                        onEvent = loginViewModel::onEvent,
                        isLoading = state.isLoading
                    )
                }

                composable<Route.Products> {
                    val viewModel = koinViewModel<ListProductViewModel>()

                    val state by viewModel.state.collectAsStateWithLifecycle()

                    when {
                        state.isLoading -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ) {
                                CircularProgressIndicator()
                            }
                        }

                        /*state.data.products.isEmpty() -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text("No Data")
                            }
                        }*/

                        else -> {
                            ListProductsScreen(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp),
                                products = state.data.products,
                                onClick = { product ->
                                    product.id?.let { id ->
                                        navController.navigate(Route.DetailProduct(productId = id))
                                    }
                                },
                                onEvent = viewModel::onEvent,
                                contentPadding = padding
                            )
                        }
                    }
                }

                composable<Route.DetailProduct> {
                    ProductDetailScreenRoot(
                        modifier = Modifier.fillMaxSize()
                    )
                }

                composable<Route.Profile> {
                    val viewModel: ProfileUserViewModel = koinViewModel()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    ProfileScreen(
                        modifier = Modifier.fillMaxSize(),
                        state = state,
                        onEvent = {
                            viewModel.onEvent(it)
                            when {
                                it is AppEvent.ProfileEvent.OnLogoutClick -> {
                                    navController.navigate(Route.Login) {
                                        popUpTo(Route.Products) {
                                            inclusive = true
                                        }
                                        launchSingleTop = true
                                    }
                                }

                                else -> Unit
                            }
                        }
                    )
                }
            }
        }
    }
}