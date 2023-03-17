package com.tcm.core.data

import com.tcm.core.BuildConfig
import com.tcm.core.data.source.local.LocalDataSource
import com.tcm.core.data.source.remote.RemoteDataSource
import com.tcm.core.data.source.remote.network.ApiResponse
import com.tcm.core.data.source.remote.response.ResultsItem
import com.tcm.core.domain.model.Popular
import com.tcm.core.domain.repository.IPopularRepository
import com.tcm.core.utils.AppExecutors
import com.tcm.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PopularRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IPopularRepository {

    override fun getAllPopular(): Flow<Resource<List<Popular>>> =
        object : NetworkBoundResource<List<Popular>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<Popular>> {
                return localDataSource.getAllPopular().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Popular>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllPopular(apiKey)

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertPopular(movieList)
            }
        }.asFlow()

    override fun getFavoritePopular(): Flow<List<Popular>> {
        return localDataSource.getFavoritePopular().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoritePopular(popular: Popular, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(popular)
        appExecutors.diskIO().execute { localDataSource.setFavoritePopular(tourismEntity, state) }
    }

    companion object {
        private const val apiKey = BuildConfig.APP_KEY
    }
}
