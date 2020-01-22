package com.example.newsapiapp.data.repositories


import com.example.newsapiapp.data.models.Listing
import com.example.newsapiapp.data.models.NewsResponse
import kotlinx.coroutines.CoroutineScope

interface ArticleRepository {
    fun getEverything(query: String, pageSize: Int, scope: CoroutineScope): Listing<NewsResponse.Article>
    fun getTopHeadline(query: String, pageSize: Int, scope: CoroutineScope): Listing<NewsResponse.Article>


}