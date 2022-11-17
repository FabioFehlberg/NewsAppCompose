package com.fberg.newsapp.feature.data.remote

import com.fberg.newsapp.common.Constants.API_KEY
import com.fberg.newsapp.feature.data.remote.dto.HeadlinesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category: String,
        @Query("q") search: String,
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY,
    ): HeadlinesDto
}
