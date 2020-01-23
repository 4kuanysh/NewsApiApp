package com.example.newsapiapp.data.repositories

import android.util.Log
import androidx.paging.PagedList
import com.example.newsapiapp.data.database.dao.ArticleDao
import com.example.newsapiapp.data.models.ArticleType
import com.example.newsapiapp.data.models.NewsResponse
import com.example.newsapiapp.data.network.RetrofitBuilder
import com.example.newsapiapp.utils.PagingRequestHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.concurrent.Executor

class NewsBoundaryCallback(
    private val articleType: ArticleType,
    private val articleDao: ArticleDao,
    private val executor: Executor,
    private val networkPageSize: Int,
    private val query: String,
    private val scope: CoroutineScope
) : PagedList.BoundaryCallback<NewsResponse.Article>() {

    private val halper = PagingRequestHelper(executor)

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        halper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) { halperCallback ->
            scope.launch {
                try {
                    val response = when (articleType) {
                        ArticleType.EVERYTHING -> RetrofitBuilder.newsService.getEverything(query, 1, networkPageSize)
                        ArticleType.TOP_HEADLINE -> RetrofitBuilder.newsService.getTopHeadlines(query, 1, networkPageSize)
                    }
                    response.articles.forEach { it.page = 1; it.type = articleType.name }
                    Log.d("taaag", "onZeroItemsLoaded ${response.articles[0].page}")
                    articleDao.insertArticles(response.articles)
                    halperCallback.recordSuccess()
                } catch (e: Exception) {
                    Log.d("taaag", "Error ${e.message}")
                    halperCallback.recordFailure(e)
                }
            }
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: NewsResponse.Article) {
        super.onItemAtEndLoaded(itemAtEnd)
        halper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) { halperCallback ->
            scope.launch {
                try {
                    val response = when (articleType) {
                        ArticleType.EVERYTHING -> RetrofitBuilder.newsService.getEverything(query, itemAtEnd.page + 1, networkPageSize)
                        ArticleType.TOP_HEADLINE -> RetrofitBuilder.newsService.getTopHeadlines(query, itemAtEnd.page + 1, networkPageSize)
                    }
                    response.articles.forEach { it.page = itemAtEnd.page + 1; it.type = articleType.name }
                    Log.d("taaag", "onItemAtEndLoaded ${itemAtEnd.page}")
                    Log.d("taaag", "onItemAtEndLoaded ${response.articles.map { it.page }}")

                    articleDao.insertArticles(response.articles)
                    halperCallback.recordSuccess()
                } catch (e: Exception) {
                    Log.d("taaag", "onItemAtEndLoaded ERROR: ${e.message}")
                    halperCallback.recordFailure(e)
                }
            }
        }
    }

}