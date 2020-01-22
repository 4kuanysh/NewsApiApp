package com.example.newsapiapp.ui.screens

import android.util.Log
import androidx.lifecycle.*
import com.example.newsapiapp.data.models.NetworkState

import com.example.newsapiapp.data.repositories.ArticleRepository
import com.google.gson.annotations.Until

class HomeViewModel(private val repository: ArticleRepository): ViewModel() {

    private val query = MutableLiveData<String>()
    private val listingEverything = Transformations.map(query) {
        repository.getEverything(it, 10, viewModelScope)
    }

    val articles = Transformations.switchMap(listingEverything) {
        it.pagedList
    }

    private val refreshTrigger = MutableLiveData<Until>()
    val refreshState: LiveData<NetworkState> = Transformations.switchMap(refreshTrigger) {
        listingEverything.value?.refresh?.invoke()
    }

    fun refresh() {
        refreshTrigger.value = null
    }

    fun setQuery(value: String) {
        query.value = value
    }


}