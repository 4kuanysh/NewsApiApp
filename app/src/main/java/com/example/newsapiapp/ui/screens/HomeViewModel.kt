package com.example.newsapiapp.ui.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapiapp.data.repositories.ArticleRepository

class HomeViewModel(private val repository: ArticleRepository): ViewModel() {

    private val query = MutableLiveData<String>()
    val articles = Transformations.switchMap(query) {
        repository.getEverything(it, 2, viewModelScope)
    }

    fun setQuery(value: String) {
        query.value = value
    }


}