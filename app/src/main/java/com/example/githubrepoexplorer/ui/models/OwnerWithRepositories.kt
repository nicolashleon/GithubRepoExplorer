package com.example.githubrepoexplorer.ui.models

import androidx.room.Embedded
import androidx.room.Relation

data class OwnerWithRepositories(@Embedded val owner: Owner,
                                 @Relation(parentColumn = "id", entityColumn = "id")
                                 val repos: List<Repository>) {
}