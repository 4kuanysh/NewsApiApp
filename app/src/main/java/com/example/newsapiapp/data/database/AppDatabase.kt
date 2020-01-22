package com.example.newsapiapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapiapp.data.database.dao.ArticleDao
import com.example.newsapiapp.data.models.Mark
import com.example.newsapiapp.data.models.NewsResponse

@Database(
    entities = [NewsResponse.Article::class, Mark::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        const val DB_NAME = "newsapi.db"
    }

}