package com.example.newsapiapp.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.newsapiapp.data.database.dao.ArticleDao
import com.example.newsapiapp.data.models.NewsResponse
import kotlinx.coroutines.CoroutineScope
import java.util.concurrent.Executor

class ArticleRepositoryImpl(private val articleDao: ArticleDao, private val executor: Executor): ArticleRepository {

    override fun getEverything(query: String, pageSize: Int, scope: CoroutineScope): LiveData<PagedList<NewsResponse.Article>> {
        val sourceFactory =  PagedKeyedArticleDataSource.ArticleDataSourceFactory(query, scope)

        val boundaryCallback = NewsBoundaryCallback(articleDao, executor, 20, query, scope)
        val liveData = articleDao.getArticles().toLiveData(
            config = Config(
            pageSize = pageSize,
            enablePlaceholders = false
            ),
            boundaryCallback = boundaryCallback
        )
        Log.d("taaag", "Articles From Local: ${liveData.value }")

        val livePagedList = sourceFactory.toLiveData(
            pageSize = pageSize,
            fetchExecutor = executor)

        return liveData
    }

}