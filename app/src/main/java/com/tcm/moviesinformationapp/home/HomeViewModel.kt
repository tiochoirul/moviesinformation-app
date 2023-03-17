package com.tcm.moviesinformationapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tcm.core.domain.usecase.PopularUseCase

class HomeViewModel(popularUseCase: PopularUseCase) : ViewModel() {
    val popular = popularUseCase.getAllPopular().asLiveData()
}