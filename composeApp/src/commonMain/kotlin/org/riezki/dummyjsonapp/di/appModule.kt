package org.riezki.dummyjsonapp.di

import com.russhwolf.settings.Settings
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.riezki.dummyjsonapp.app.MainViewModel
import org.riezki.dummyjsonapp.core.data.local.PreferenceRepositoryImpl
import org.riezki.dummyjsonapp.domain.RemoteDataSource
import org.riezki.dummyjsonapp.core.data.remote.RemoteDataSourceImpl
import org.riezki.dummyjsonapp.core.utils.HttpFactory
import org.riezki.dummyjsonapp.domain.PreferenceRepository
import org.riezki.dummyjsonapp.presenter.list_product.view_model.ListProductViewModel
import org.riezki.dummyjsonapp.presenter.login.view_model.LoginViewModel
import org.riezki.dummyjsonapp.presenter.product_detail.view_model.ProductDetailViewModel
import org.riezki.dummyjsonapp.presenter.profile_user.view_model.ProfileUserViewModel

/**
 * @author riezky maisyar
 */

expect val platformModule: Module

val appModule = module {
    single { Settings() }
    single<HttpClient> { HttpFactory.create(get()) }
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    singleOf(::PreferenceRepositoryImpl) { bind<PreferenceRepository>() }

    viewModelOf(::LoginViewModel)
    viewModelOf(::ListProductViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::ProductDetailViewModel)
    viewModelOf(::ProfileUserViewModel)
}
