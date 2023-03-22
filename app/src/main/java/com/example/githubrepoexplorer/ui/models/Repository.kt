package com.example.githubrepoexplorer.ui.models

data class Repository(
    val name: String,
    val fullName: String,
    val description: String,
    val visible: Boolean,
    val isPrivate: Boolean,
    val htmlUrl: String,
    val owner: Owner
)