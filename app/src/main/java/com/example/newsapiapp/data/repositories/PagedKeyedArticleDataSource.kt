package com.example.newsapiapp.data.repositories

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.newsapiapp.data.models.NewsResponse
import com.example.newsapiapp.data.network.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PagedKeyedArticleDataSource(
    private val query: String,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, NewsResponse.Article>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, NewsResponse.Article>
    ) {
        scope.launch {
            try {
                val newsResponse = RetrofitBuilder.newsService.getEverything(query, 1, params.requestedLoadSize)
                Log.d("taaag", "loadInitial 1, size: ${newsResponse.articles.size}")
                val data = newsResponse.articles
                callback.onResult(data, null, 4)
            } catch (e: HttpException) {

            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, NewsResponse.Article>
    ) {
        scope.launch {
            try {
                val newsResponse = RetrofitBuilder.newsService.getEverything(query, params.key, params.requestedLoadSize)
                Log.d("taaag", "loadAfter: ${params.key}, size: ${newsResponse.articles.size}")
                val data = newsResponse.articles
                callback.onResult(data, params.key + 1)
            } catch (e: HttpException) {

            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, NewsResponse.Article>
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ArticleDataSourceFactory(
        private val query: String,
        private val scope: CoroutineScope
    ): DataSource.Factory<Int, NewsResponse.Article>() {
        override fun create(): DataSource<Int, NewsResponse.Article> {
            return PagedKeyedArticleDataSource(query, scope)
        }
    }
}