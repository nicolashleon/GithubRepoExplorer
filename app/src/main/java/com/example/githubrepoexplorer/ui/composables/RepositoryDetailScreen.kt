package com.example.githubrepoexplorer.ui.composables

import FullScreenError
import GithubRepoExplorerTheme
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubrepoexplorer.R
import com.example.githubrepoexplorer.ui.RepositoryDetailViewModel
import com.example.githubrepoexplorer.ui.UIState
import com.example.githubrepoexplorer.ui.models.OwnerRepoTuple
import org.koin.androidx.compose.get
import org.koin.core.parameter.parametersOf


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryDetailScreen(repoId: Long, onBackPressed: () -> Unit = {}) {
    val viewModel: RepositoryDetailViewModel = get {
        parametersOf(repoId)
    }

    val ownerRepoTuple by viewModel.ownerRepoTupleLiveData.observeAsState()

    LaunchedEffect(key1 = repoId) {
        viewModel.loadRepositoryDetails()
    }

    GithubRepoExplorerTheme {
        Scaffold(topBar = {
            TopAppBar(title = {}, navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            })
        }) { paddingValues ->
            ownerRepoTuple?.let {
                RepositoryDetailComposable(Modifier.padding(paddingValues), it) {
                    viewModel.loadRepositoryDetails()
                }
            }
        }

    }
}

@Composable
fun RepositoryDetailComposable(
    modifier: Modifier = Modifier,
    repoUIState: UIState<OwnerRepoTuple>,
    onErrorRetry: () -> Unit
) {

    val uriHandler = LocalUriHandler.current
    GithubRepoExplorerTheme {
        Column(modifier = modifier.fillMaxHeight()) {
            when (repoUIState) {
                is UIState.Loading -> {
                    Box(modifier = modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Center))
                    }
                }
                is UIState.Error -> {
                    FullScreenError(
                        error = stringResource(id = R.string.txt_error_loading_repo_detail),
                        onRetry = onErrorRetry
                    )
                }
                is UIState.Success -> {
                    val repo = repoUIState.data
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1F)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = repo.name,
                            style = Typography().headlineMedium,
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .padding(bottom = 32.dp, start = 16.dp, end = 16.dp, top = 16.dp)
                        )

                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(repo.ownerImage)
                                .crossfade(true)
                                .build(),
                            placeholder = null,
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .size(150.dp)
                                .clip(CircleShape)
                        )

                        Text(
                            text = repo.fullName,
                            style = Typography().labelMedium.copy(color = colorResource(id = R.color.gray)),
                            modifier = Modifier
                                .padding(16.dp)
                                .align(CenterHorizontally),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = null,
                                tint = colorResource(id = R.color.gray)
                            )
                            Spacer(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
                            Text(text = repo.visibility.displayTextResource?.let { stringResource(id = it) }.orEmpty())
                        }

                        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null,
                                tint = colorResource(id = R.color.gray)
                            )
                            Spacer(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
                            Text(
                                stringResource(
                                    id = when (repo.isPrivate) {
                                        true -> R.string.txt_repo_is_private
                                        else -> R.string.txt_repo_is_public
                                    }
                                )
                            )
                        }

                        Text(
                            text = repo.description,
                            style = typography.labelMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                        )

                    }

                    Button(
                        onClick = {
                            uriHandler.openUri(repo.htmlUrl)
                        },
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 24.dp)
                    ) {
                        Text(text = stringResource(id = R.string.txt_visit_repo_website))
                    }
                }
            }
        }

    }
}