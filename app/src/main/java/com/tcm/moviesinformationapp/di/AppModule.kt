package com.tcm.moviesinformationapp.di

import com.tcm.core.session.SessionManager
import com.tcm.core.domain.usecase.PopularInteractor
import com.tcm.core.domain.usecase.PopularUseCase
import com.tcm.moviesinformationapp.detail.DetailPopularViewModel
import com.tcm.moviesinformationapp.home.HomeViewModel
import com.tcm.moviesinformationapp.settings.SettingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<PopularUseCase> { PopularInteractor(get()) }
}

val sessionManagerModule = module {
    single { SessionManager(androidContext()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailPopularViewModel(get()) }
    viewModel { SettingViewModel(get()) }
}