package com.example.githubrepoexplorer.di

import androidx.room.Room
import com.example.githubrepoexplorer.data.local.LocalOwnerWithReposDataSource
import com.example.githubrepoexplorer.data.local.RepositoryDatabase
import com.example.githubrepoexplorer.data.network.AuthInterceptor
import com.example.githubrepoexplorer.data.network.RemoteOwnerWithReposDataSource
import com.example.githubrepoexplorer.data.network.RepositoryService
import com.example.githubrepoexplorer.ui.RepositoryDetailViewModel
import com.example.githubrepoexplorer.ui.RepositoryListViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Room.databaseBuilder(
            get(),
            RepositoryDatabase::class.java,
            "repository-database"
        ).build()
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()
    }
    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    factory {
        get<Retrofit>().create(RepositoryService::class.java)
    }
    factory { LocalOwnerWithReposDataSource(get()) }
    factory { RemoteOwnerWithReposDataSource(get()) }
    viewModel { RepositoryListViewModel(get(), get()) }
    viewModel { params -> RepositoryDetailViewModel(params.get(), get()) }
}