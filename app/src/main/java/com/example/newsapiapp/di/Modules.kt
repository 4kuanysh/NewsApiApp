package com.example.newsapiapp.di

import androidx.room.Room
import com.example.newsapiapp.data.database.AppDatabase
import com.example.newsapiapp.data.database.dao.ArticleDao
import com.example.newsapiapp.data.repositories.ArticleRepositoryImpl
import com.example.newsapiapp.ui.screens.home.HomeViewModel
import com.example.newsapiapp.ui.screens.my_list.MyListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.Executors

val dbModule = module {
    single { Room.databaseBuilder(androidContext(),
        AppDatabase::class.java, AppDatabase.DB_NAME).build() }

    single { get<AppDatabase>().getArticleDao() }
}

val repoModule = module {
    single { ArticleRepositoryImpl(get() as ArticleDao, Executors.newSingleThreadExecutor()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get() as ArticleRepositoryImpl) }
    viewModel { MyListViewModel(get() as ArticleRepositoryImpl) }
}