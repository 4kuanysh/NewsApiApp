package com.example.newsapiapp.data.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.example.newsapiapp.data.models.NewsResponse

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: NewsResponse.Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<NewsResponse.Article>)

    @Query("SELECT * FROM Article")
    fun getArticles(): DataSource.Factory<Int, NewsResponse.Article>

    @Delete
    suspend fun deleteArticle(article: NewsResponse.Article)

}