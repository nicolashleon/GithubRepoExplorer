package com.example.githubrepoexplorer.ui.composables

import GithubRepoExplorerTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.PublicOff
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubrepoexplorer.R
import com.example.githubrepoexplorer.ui.models.Owner
import com.example.githubrepoexplorer.ui.models.Repository


fun getPreviewRepoUIModel() = Repository(
    "MyRepo",
    "my repo full name",
    "This is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repo",
    true,
    isPrivate = false,
    "http://github.com/nicolashleon",
    Owner("nicolashleon", "https://avatars.githubusercontent.com/u/15876397?v=4")
)

@Composable
fun RepositoryItemComposablePreview(onClick: () -> Unit = {}) {
    RepositoryItemComposable(
        repo = getPreviewRepoUIModel(), onClick = onClick
    )
}

@Composable
fun RepositoryItemComposable(modifier: Modifier = Modifier, repo: Repository, onClick: () -> Unit = {}) {
    GithubRepoExplorerTheme {
        Row(
            modifier = modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(repo.owner.image)
                    .crossfade(true)
                    .build(),
                placeholder = null,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(72.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(start = 16.dp))
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.weight(1F)

            ) {
                Text(text = repo.name, style = typography.headlineSmall, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Row(verticalAlignment = CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = when (repo.visible) {
                            true -> Icons.Default.Visibility
                            else -> Icons.Default.VisibilityOff
                        },
                        contentDescription = null,
                        tint = colorResource(id = R.color.gray)
                    )
                    Text(
                        stringResource(
                            id = when (repo.visible) {
                                true -> R.string.txt_visible
                                else -> R.string.txt_hidden
                            }
                        ), style = typography.labelMedium.copy(color = colorResource(id = R.color.gray)),
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.padding(top = 8.dp))
                Row(verticalAlignment = CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = when (repo.isPrivate) {
                            true -> Icons.Default.PublicOff
                            else -> Icons.Default.Public
                        },
                        contentDescription = null,
                        tint = colorResource(id = R.color.gray)
                    )
                    Text(
                        stringResource(
                            id = when (repo.isPrivate) {
                                true -> R.string.txt_public
                                else -> R.string.txt_private
                            }
                        ), style = typography.labelMedium.copy(color = colorResource(id = R.color.gray)),
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .fillMaxWidth()
                    )
                }

            }
        }
    }
}