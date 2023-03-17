package com.tcm.core.data.source.local

import com.tcm.core.data.source.local.entity.PopularEntity
import com.tcm.core.data.source.local.room.PopularDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val popularDao: PopularDao) {

    fun getAllPopular(): Flow<List<PopularEntity>> = popularDao.getAllPopular()

    fun getFavoritePopular(): Flow<List<PopularEntity>> = popularDao.getFavoritePopular()

    suspend fun insertPopular(popularList: List<PopularEntity>) =
        popularDao.insertPopular(popularList)

    fun setFavoritePopular(popular: PopularEntity, newState: Boolean) {
        popular.isFavorite = newState
        popularDao.updateFavoritePopular(popular)
    }
}