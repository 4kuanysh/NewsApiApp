package com.example.newsapiapp.data.network

import com.example.newsapiapp.BuildConfig
import com.example.newsapiapp.data.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/everything/")
    suspend fun getEverything(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apyKey: String? = BuildConfig.NEWS_API_KEY
    ): NewsResponse

    @GET("v2/top-headlines/")
    suspend fun getTopHeadlines(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apyKey: String? = BuildConfig.NEWS_API_KEY
    ): NewsResponse
}