package com.tcm.core.domain.usecase

import com.tcm.core.domain.model.Popular
import com.tcm.core.domain.repository.IPopularRepository

class PopularInteractor(private val popularRepository: IPopularRepository) : PopularUseCase {
    override fun getAllPopular() = popularRepository.getAllPopular()

    override fun getFavoritePopular() = popularRepository.getFavoritePopular()

    override fun setFavoritePopular(popular: Popular, state: Boolean) =
        popularRepository.setFavoritePopular(popular, state)
}