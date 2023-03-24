package com.example.githubrepoexplorer.ui.composables

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.githubrepoexplorer.ui.models.Owner
import com.example.githubrepoexplorer.ui.models.Repository
import com.example.githubrepoexplorer.ui.models.Visibility

class RepositoryPreviewProvider : PreviewParameterProvider<RepositoryOwnerPreview> {
    override val values: Sequence<RepositoryOwnerPreview>
        get() = listOf(getRepositoryPreview()).asSequence()


}

data class RepositoryOwnerPreview(val repository: Repository, val owner: Owner)

fun getRepositoryPreview() = RepositoryOwnerPreview(
    Repository(
        12313,
        "My repo",
        "This is the repo full name",
        "This is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repoThis is a beautiful repo",
        Visibility.PUBLIC,
        false,
        "http://github.com/nicolashleon",
        1234
    ), Owner(1234, "nicolashleon", "https://avatars.githubusercontent.com/u/15876397?v=4")
)