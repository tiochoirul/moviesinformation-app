package com.tcm.core.data.source.remote

import android.util.Log
import com.tcm.core.data.source.remote.network.ApiResponse
import com.tcm.core.data.source.remote.network.ApiService
import com.tcm.core.data.source.remote.response.ResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllPopular(apiKey: String): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val response = apiService.getList(apiKey)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}