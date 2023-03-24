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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
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
import com.example.githubrepoexplorer.ui.models.OwnerRepoTuple

@Composable
fun RepositoryItemComposable(modifier: Modifier = Modifier, repoOwnerRepoTuple: OwnerRepoTuple, onClick: () -> Unit = {}) {
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
                    .data(repoOwnerRepoTuple.ownerImage)
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
                Text(text = repoOwnerRepoTuple.name, style = typography.headlineSmall, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Row(verticalAlignment = CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = null,
                        tint = colorResource(id = R.color.gray)
                    )
                    Text(
                        text = repoOwnerRepoTuple.visibility.displayTextResource?.let { stringResource(id = it) }.orEmpty(),
                        style = typography.labelMedium.copy(color = colorResource(id = R.color.gray)),
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.padding(top = 8.dp))
                Row(verticalAlignment = CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = colorResource(id = R.color.gray)
                    )
                    Text(
                        stringResource(
                            id = when (repoOwnerRepoTuple.isPrivate) {
                                true -> R.string.txt_repo_is_private
                                else -> R.string.txt_repo_is_public
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