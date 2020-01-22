package com.example.newsapiapp.ui.screens

import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import androidx.lifecycle.*
import com.example.newsapiapp.data.models.NetworkState

import com.example.newsapiapp.data.repositories.ArticleRepository
import com.google.gson.annotations.Until
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class HomeViewModel(private val repository: ArticleRepository): ViewModel() {

    private val handler = Handler()

    private val query = MutableLiveData<String>()
    private val listingEverything = Transformations.map(query) {
        repository.getEverything(it, 10, viewModelScope)
    }
    private val listingTopHeadlines = Transformations.map(query) {
        repository.getTopHeadline(it, 10, viewModelScope)
    }

    val everythingArticles = Transformations.switchMap(listingEverything) {
        it.pagedList
    }

    val topHeadlinesArticles = Transformations.switchMap(listingTopHeadlines) {
        it.pagedList
    }

    private val refreshEverythingTrigger = MutableLiveData<Until>()
    val refreshEverythingState: LiveData<NetworkState> = Transformations.switchMap(refreshEverythingTrigger) {
        listingEverything.value?.refresh?.invoke()
    }

    fun refreshEverything() {
        refreshEverythingTrigger.value = null
    }

    private val refreshTopHeadlinesTrigger = MutableLiveData<Until>()
    val refreshTopHeadlinesState: LiveData<NetworkState> = Transformations.switchMap(refreshTopHeadlinesTrigger) {
        listingEverything.value?.refresh?.invoke()
    }



    fun refreshTopHeadlines() {
        val timer = Timer()
        val timerTask = object : TimerTask() {
            override fun run() {
                Log.d("taaag", "refreshTopHeadlines()")
                refreshTopHeadlinesTrigger.postValue(null)
            }
        }
        timer.schedule(timerTask, 0, 5000L);
    }

    fun setQuery(value: String) {
        query.value = value
    }


}