package com.example.githubrepoexplorer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepoexplorer.data.local.LocalOwnerWithReposDataSource
import com.example.githubrepoexplorer.ui.models.OwnerRepoTuple
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepositoryDetailViewModel(private val repoId: Long, private val localSource: LocalOwnerWithReposDataSource): ViewModel() {

    private val internalOwnerRepoTupleLiveData = MutableLiveData<UIState<OwnerRepoTuple>>()
    val ownerRepoTupleLiveData: LiveData<UIState<OwnerRepoTuple>> = internalOwnerRepoTupleLiveData

    fun loadRepositoryDetails() {
        viewModelScope.launch {
            internalOwnerRepoTupleLiveData.value = UIState.Loading
            val result = withContext(Dispatchers.IO) {
                localSource.getRepoWithOwner(repoId)
            }
            internalOwnerRepoTupleLiveData.value = UIState.Success(result)
        }
    }
}