package com.example.githubrepoexplorer.ui.composables

import FullScreenEmptyState
import FullScreenError
import GithubRepoExplorerTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.githubrepoexplorer.R
import com.example.githubrepoexplorer.ui.RepositoryListViewModel
import com.example.githubrepoexplorer.ui.models.OwnerRepoTuple
import org.koin.androidx.compose.get


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RepositoryListScreen(onNavigateToRepoDetail: (repoId: Long) -> Unit) {

    val repositoryListViewModel: RepositoryListViewModel = get()

    val reposAndOwner: LazyPagingItems<OwnerRepoTuple> =
        repositoryListViewModel.repositoriesPager.collectAsLazyPagingItems()

    var isLoadingNewValues = reposAndOwner.loadState.refresh is LoadState.Loading
    var isErrorVisible = reposAndOwner.loadState.refresh is LoadState.Error && reposAndOwner.itemCount == 0
    var isEmptyStateVisible = reposAndOwner.loadState.refresh is LoadState.NotLoading && reposAndOwner.itemCount == 0
    var shouldDisplayRepos = !(isEmptyStateVisible || isErrorVisible)

    var listState: LazyListState? = if(shouldDisplayRepos) { rememberLazyListState() } else { null }

    val pullRefreshState = rememberPullRefreshState(false, {
        reposAndOwner.refresh()
    })


    GithubRepoExplorerTheme {
        Scaffold(topBar = {
            TopAppBar(title = {
                if (shouldDisplayRepos) {
                    Text(text = stringResource(id = R.string.txt_repositories))
                }
            })
        }) {
            Surface(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState),
                color = MaterialTheme.colorScheme.background
            ) {
                Box {
                    if (shouldDisplayRepos) {
                        if (listState != null) {
                            RepositoryList(listState, reposAndOwner, onNavigateToRepoDetail)
                        }
                        if (isLoadingNewValues) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                        PullRefreshIndicator(
                            refreshing = false,
                            state = pullRefreshState,
                            modifier = Modifier.align(Alignment.TopCenter)
                        )
                    } else if (isErrorVisible) {
                        FullScreenError(error = stringResource(id = R.string.txt_err_fetching_repos)) {
                            reposAndOwner.retry()
                        }
                    } else if (isEmptyStateVisible) {
                        FullScreenEmptyState(message = stringResource(id = R.string.txt_empty_repos)) {
                            reposAndOwner.retry()
                        }
                    }

                }
            }
        }

    }
}


@Composable
fun RepositoryList(listState: LazyListState, reposWithOwner: LazyPagingItems<OwnerRepoTuple>, onNavigateToRepoDetail: (repoId: Long) -> Unit = {}) {
    LazyColumn(modifier = Modifier.fillMaxHeight(), state = listState) {
        items(reposWithOwner) {
            if (it != null) {
                RepositoryItemComposable(repoOwnerRepoTuple = it, onClick = { onNavigateToRepoDetail(it.id) })
                Divider(color = colorResource(id = R.color.light_gray))
            }
        }
    }
}