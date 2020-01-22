package com.example.newsapiapp.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.newsapiapp.data.database.dao.ArticleDao
import com.example.newsapiapp.data.models.*
import com.example.newsapiapp.data.network.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.concurrent.Executor

class ArticleRepositoryImpl(private val articleDao: ArticleDao, private val executor: Executor): ArticleRepository {

    override fun getEverything(query: String, pageSize: Int, scope: CoroutineScope): Listing<NewsResponse.Article> {
        return getArticles(ArticleType.EVERYTHING, query, pageSize, scope)
    }

    override fun getTopHeadline(query: String, pageSize: Int, scope: CoroutineScope): Listing<NewsResponse.Article> {
        return getArticles(ArticleType.TOP_HEADLINE, query, pageSize, scope)
    }

    override fun getMyArticles(pageSize: Int): LiveData<PagedList<NewsResponse.Article>> =
        articleDao.getMyArticles().toLiveData(pageSize = pageSize)

    override suspend fun insertMyArticle(title: String) {
        withContext(IO) {
            articleDao.insertMark(Mark(title))
        }
    }

    private fun getArticles(articleType: ArticleType, query: String, pageSize: Int, scope: CoroutineScope): Listing<NewsResponse.Article> {
        val boundaryCallback = NewsBoundaryCallback(articleType, articleDao, executor, 20, query, scope)
        val liveData = articleDao.getArticlesByType(articleType.name).toLiveData(
            config = Config(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            boundaryCallback = boundaryCallback
        )

        val refresh : (scope: CoroutineScope) -> LiveData<NetworkState> = {
            val networkState = MutableLiveData<NetworkState>()
            networkState.value = NetworkState.LOADING
            it.launch {
                try {
                    val response = RetrofitBuilder.newsService.getEverything(query, 1, pageSize)
                    response.articles.forEach { art -> art.page = 1; art.type = articleType.name }
                    articleDao.deleteArticles(articleType.name)
                    articleDao.insertArticles(response.articles)
                    networkState.value = NetworkState.LOADED
                } catch (e : Exception) {
                    networkState.value = NetworkState.error(e.message)
                }
            }
            networkState
        }


        return Listing(
            pagedList = liveData,
            refresh = {
                refresh(scope)
            }
        )
    }

}