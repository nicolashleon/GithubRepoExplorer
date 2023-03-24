package com.example.githubrepoexplorer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.githubrepoexplorer.BuildConfig
import com.example.githubrepoexplorer.data.RepositoryMediator
import com.example.githubrepoexplorer.data.local.LocalOwnerWithReposDataSource
import com.example.githubrepoexplorer.data.network.RemoteOwnerWithReposDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class RepositoryListViewModel(
    networkOwnerWithReposImpl: RemoteOwnerWithReposDataSource,
    private val localOwnerWithReposDataSource: LocalOwnerWithReposDataSource
) : ViewModel() {

    private val username: String = BuildConfig.REPO_USER_VALUE

    @OptIn(ExperimentalPagingApi::class)
    val repositoriesPager = Pager(
        config = PagingConfig(PAGE_SIZE),
        remoteMediator = RepositoryMediator(username, localOwnerWithReposDataSource, networkOwnerWithReposImpl)
    ) {
        localOwnerWithReposDataSource.getOwnerWithRepositories(username)
    }.flow.flowOn(Dispatchers.IO).cachedIn(viewModelScope)

    companion object {
        private const val PAGE_SIZE = 5
    }

}