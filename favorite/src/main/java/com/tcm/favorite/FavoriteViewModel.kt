package com.tcm.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tcm.core.domain.usecase.PopularUseCase

class FavoriteViewModel(popularUseCase: PopularUseCase) : ViewModel() {
    val favoriteMovie = popularUseCase.getFavoritePopular().asLiveData()
}

