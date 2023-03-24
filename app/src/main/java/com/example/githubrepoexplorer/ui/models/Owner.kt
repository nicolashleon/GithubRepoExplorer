package com.example.githubrepoexplorer.ui.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Owner(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Long,

    @ColumnInfo("login")
    val login: String,

    @ColumnInfo("image")
    val image: String
)