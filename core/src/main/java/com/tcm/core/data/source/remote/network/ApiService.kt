package com.tcm.core.data.source.remote.network

import com.tcm.core.data.source.remote.response.PopularResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getList(
        @Query("api_key") api_key: String
    ): PopularResponse

}