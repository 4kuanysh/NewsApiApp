package com.example.newsapiapp.data.repositories


import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.newsapiapp.data.models.Listing
import com.example.newsapiapp.data.models.NewsResponse
import kotlinx.coroutines.CoroutineScope

interface ArticleRepository {
    fun getEverything(query: String, pageSize: Int, scope: CoroutineScope): Listing<NewsResponse.Article>
    fun getTopHeadline(query: String, pageSize: Int, scope: CoroutineScope): Listing<NewsResponse.Article>
    fun getMyArticles(pageSize: Int): LiveData<PagedList<NewsResponse.Article>>
    suspend fun insertMyArticle(title: String)

}