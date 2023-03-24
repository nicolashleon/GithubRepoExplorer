package com.example.githubrepoexplorer.ui.models

data class OwnerRepoTuple(
    val ownerId: Long,

    val ownerLogin: String,

    val ownerImage: String,

    val id: Long,

    val name: String,

    val fullName: String,

    val description: String,

    val visibility: Visibility,

    val isPrivate: Boolean,

    val htmlUrl: String
)