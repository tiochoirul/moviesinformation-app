package com.tcm.moviesinformationapp.detail

import androidx.lifecycle.ViewModel
import com.tcm.core.domain.model.Popular
import com.tcm.core.domain.usecase.PopularUseCase

class DetailPopularViewModel(private val popularUseCase: PopularUseCase) : ViewModel() {
    fun setFavoritePopular(popular: Popular, newStatus: Boolean) =
        popularUseCase.setFavoritePopular(popular, newStatus)
}