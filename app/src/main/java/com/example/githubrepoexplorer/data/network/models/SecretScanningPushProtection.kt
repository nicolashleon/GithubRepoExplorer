package com.example.githubrepoexplorer.data.network.models


import com.google.gson.annotations.SerializedName

data class SecretScanningPushProtection(
    @SerializedName("status")
    val status: String = ""
)