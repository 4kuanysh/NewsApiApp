package com.example.newsapiapp.data.models

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class Listing<T>(
    val pagedList: LiveData<PagedList<T>>,
//    val networkState: LiveData<NetworkState>,
    val refresh: () -> LiveData<NetworkState>
)