package com.example.githubrepoexplorer.ui.composables

import GithubRepoExplorerTheme
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.PublicOff
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubrepoexplorer.R
import com.example.githubrepoexplorer.ui.models.Repository


@Preview
@Composable
fun RepositoryDetailScreenPreview(onBackPressed: () -> Unit = {}) {
    RepositoryDetailScreen(onBackPressed)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryDetailScreen(onBackPressed: () -> Unit = {}) {
    GithubRepoExplorerTheme {
        Scaffold(topBar = {
            TopAppBar(title = {}, navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            })
        }, content = {
            RepositoryDetailComposable(Modifier.padding(it), getPreviewRepoUIModel())
        })

    }
}

@Composable
fun RepositoryDetailComposable(modifier: Modifier = Modifier, repo: Repository) {

    val uriHandler = LocalUriHandler.current

    GithubRepoExplorerTheme {
        Column(modifier) {
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
                        .data(repo.owner.image)
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
                        imageVector = if (repo.visible) {
                            Icons.Default.Visibility
                        } else {
                            Icons.Default.VisibilityOff
                        },
                        contentDescription = null,
                        tint = colorResource(id = R.color.gray)
                    )
                    Spacer(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
                    Icon(
                        imageVector = if (repo.isPrivate) {
                            Icons.Default.PublicOff
                        } else {
                            Icons.Default.Public
                        },
                        contentDescription = null,
                        tint = colorResource(id = R.color.gray)
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