package com.tcm.core.domain.usecase

import com.tcm.core.data.Resource
import com.tcm.core.domain.model.Popular
import kotlinx.coroutines.flow.Flow

interface PopularUseCase {
    fun getAllPopular(): Flow<Resource<List<Popular>>>
    fun getFavoritePopular(): Flow<List<Popular>>
    fun setFavoritePopular(popular: Popular, state: Boolean)
}