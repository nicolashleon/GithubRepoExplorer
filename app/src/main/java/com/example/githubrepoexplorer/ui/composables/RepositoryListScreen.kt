package com.example.githubrepoexplorer.ui.composables

import FullScreenEmptyState
import FullScreenError
import GithubRepoExplorerTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
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
import com.example.githubrepoexplorer.R
import com.example.githubrepoexplorer.ui.models.Repository


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RepositoryListScreen(onNavigateToRepoDetail: () -> Unit) {
    GithubRepoExplorerTheme {

        //TODO connect with view model
        var isErrorVisible by remember { mutableStateOf(false) }
        var isEmptyStateVisible by remember { mutableStateOf(false) }
        var shouldDisplayRepos by remember { mutableStateOf(!(isEmptyStateVisible || isErrorVisible)) }

        val pullRefreshState = rememberPullRefreshState(false, {
            //TODO handle call to trigger the refresh
            println("Refreshing")
        })

        //TODO get the data from the view model
        val repoList = mutableListOf<Repository>()
        for (i in 0 .. 99) {
            repoList.add(getPreviewRepoUIModel())
        }

        Scaffold(topBar = {
            TopAppBar(title = {
                if(shouldDisplayRepos) {
                    Text(text = stringResource(id = R.string.txt_repositories))
                }
            })
        }, content = {
            Surface(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState),
                color = MaterialTheme.colorScheme.background
            ) {
                Box {
                    if(shouldDisplayRepos) {
                        //TODO add logic to handle pagination
                        RepositoryList(repoList, onNavigateToRepoDetail)
                        PullRefreshIndicator(
                            refreshing = false,
                            state = pullRefreshState,
                            modifier = Modifier.align(Alignment.TopCenter)
                        )
                    } else if(isErrorVisible) {
                        FullScreenError(error = stringResource(id = R.string.txt_err_fetching_repos))
                    } else if (isEmptyStateVisible) {
                        FullScreenEmptyState(message = stringResource(id = R.string.txt_empty_repos))
                    }

                }
            }
        })

    }
}


@Composable
fun RepositoryList(repositories: List<Repository>, onNavigateToRepoDetail: () -> Unit = {}) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(repositories) {
            RepositoryItemComposable(repo = it, onClick = onNavigateToRepoDetail)
            Divider(color = colorResource(id = R.color.gray))
        }
    }
}