package com.example.githubrepoexplorer.ui.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Repository(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("full_name")
    val fullName: String,

    @ColumnInfo("description")
    val description: String,

    @ColumnInfo("visibility")
    val visibility: Visibility,

    @ColumnInfo("private")
    val isPrivate: Boolean,

    @ColumnInfo("html_url")
    val htmlUrl: String,

    @ColumnInfo("owner_id")
    val ownerId: Long
)