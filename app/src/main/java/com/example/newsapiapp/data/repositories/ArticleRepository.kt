package com.example.newsapiapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.newsapiapp.data.models.NewsResponse
import kotlinx.coroutines.CoroutineScope

interface ArticleRepository {
    fun getEverything(query: String, pageSize: Int, scope: CoroutineScope): LiveData<PagedList<NewsResponse.Article>>
}