package com.example.newsapiapp.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newsapiapp.data.models.NewsResponse
import com.example.newsapiapp.data.repositories.ArticleRepository

class MyListViewModel(private val repository: ArticleRepository): ViewModel() {

    var articles = repository.getMyArticles(15)

}