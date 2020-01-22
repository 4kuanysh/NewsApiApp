package com.example.newsapiapp.data.models
import androidx.room.*
import com.google.gson.annotations.SerializedName

enum class ArticleType { EVERYTHING, TOP_HEADLINE }

data class NewsResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
) {
    @Entity(tableName = "Article")
    data class Article(
        @SerializedName("author")
        @ColumnInfo(name = "author")
        val author: String?,
        @SerializedName("content")
        @ColumnInfo(name = "content")
        val content: String?,
        @SerializedName("description")
        @ColumnInfo(name = "description")
        val description: String?,
        @SerializedName("publishedAt")
        @ColumnInfo(name = "publishedAt")
        val publishedAt: String?,
        @SerializedName("title")
        @PrimaryKey
        val title: String,
        @SerializedName("url")
        @ColumnInfo(name = "url")
        val url: String?,
        @SerializedName("urlToImage")
        @ColumnInfo(name = "urlToImage")
        val urlToImage: String?,
        @ColumnInfo(name = "page")
        var page: Int,
        @ColumnInfo(name = "type")
        var type: String
    )
}