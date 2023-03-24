package com.example.githubrepoexplorer.data.network.models


import com.google.gson.annotations.SerializedName

data class Permissions(
    @SerializedName("admin")
    val admin: Boolean = false,
    @SerializedName("pull")
    val pull: Boolean = false,
    @SerializedName("push")
    val push: Boolean = false
)