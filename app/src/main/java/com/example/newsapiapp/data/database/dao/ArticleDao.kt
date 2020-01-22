package com.example.newsapiapp.data.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.example.newsapiapp.data.models.NewsResponse

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<NewsResponse.Article>)

    @Query("SELECT * FROM Article")
    fun getArticles(): DataSource.Factory<Int, NewsResponse.Article>

    @Query("SELECT * FROM Article WHERE Article.type == :articleType")
    fun getArticlesByType(articleType: String): DataSource.Factory<Int, NewsResponse.Article>

    @Query("DELETE FROM Article")
    suspend fun deleteArticles()

}