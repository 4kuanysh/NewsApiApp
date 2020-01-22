package com.example.newsapiapp.data.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.example.newsapiapp.data.models.Mark
import com.example.newsapiapp.data.models.NewsResponse

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<NewsResponse.Article>)

    @Query("SELECT * FROM Article")
    fun getArticles(): DataSource.Factory<Int, NewsResponse.Article>

    @Query("SELECT * FROM Article WHERE Article.type == :articleType")
    fun getArticlesByType(articleType: String): DataSource.Factory<Int, NewsResponse.Article>

    @Query("DELETE FROM Article WHERE Article.type == :articleType")
    suspend fun deleteArticles(articleType: String)

    @Insert
    suspend fun insertMark(mark: Mark)

    @Query("SELECT * FROM Article, Mark WHERE Article.title == Mark.title")
    fun getMyArticles(): DataSource.Factory<Int, NewsResponse.Article>

    //Test
    @Query("SELECT * FROM  Mark")
    fun getAllMarks(): List<Mark>

}