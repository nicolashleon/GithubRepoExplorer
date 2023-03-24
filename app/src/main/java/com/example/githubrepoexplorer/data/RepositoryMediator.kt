package com.example.githubrepoexplorer.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.githubrepoexplorer.data.local.LocalOwnerWithReposDataSource
import com.example.githubrepoexplorer.data.network.RemoteOwnerWithReposDataSource
import com.example.githubrepoexplorer.logError
import com.example.githubrepoexplorer.ui.models.OwnerRepoTuple
import com.example.githubrepoexplorer.ui.models.RemoteKey

@OptIn(ExperimentalPagingApi::class)
class RepositoryMediator(
    private val user: String,
    private val localRepo: LocalOwnerWithReposDataSource,
    private val networkRepo: RemoteOwnerWithReposDataSource
) : RemoteMediator<Int, OwnerRepoTuple>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, OwnerRepoTuple>
    ): MediatorResult {
        return try {

            val initialKey = getInitialRemoteKey()
            val loadKey = when (loadType) {
                REFRESH -> initialKey
                PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                APPEND -> {
                    val currentKey = localRepo.getRemoteKeyForRepositories(initialKey.id)
                    currentKey?.copy(page = currentKey.page + 1) ?: initialKey
                }
            }

            val response = networkRepo.getRepositories(user, loadKey.page, state.config.pageSize)

            if (response.isFailure) {
                throw response.exceptionOrNull() ?: Exception("Error while fetching the data")
            }
            val data = response.getOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)

            if (loadType == REFRESH) {
                localRepo.clearAndInsertRepositoriesAndOwners(loadKey, data)
            } else {
                localRepo.insertOwnerWithRepositories(loadKey, data)
            }

            MediatorResult.Success(
                endOfPaginationReached = false
            )
        } catch (e: Throwable) {
            logError("Error while retrieving the data from the service and saving it to the DB", e)
            MediatorResult.Error(e)
        }
    }


    private fun getInitialRemoteKey() = RemoteKey(OwnerRepoTuple::class.hashCode(), 0)

}
