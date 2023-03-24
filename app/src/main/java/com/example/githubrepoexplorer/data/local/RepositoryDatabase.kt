package com.example.githubrepoexplorer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubrepoexplorer.ui.models.Owner
import com.example.githubrepoexplorer.ui.models.RemoteKey
import com.example.githubrepoexplorer.ui.models.Repository


@Database(version = 1, entities = [Owner::class, Repository::class, RemoteKey::class])
abstract class RepositoryDatabase : RoomDatabase() {
    abstract fun getRepositoryDao(): RepositoryDao
    abstract fun getOwnerDao(): OwnerDao
    abstract fun getOwnerWithRepositoriesDao(): OwnerWithRepositoriesDao
    abstract fun getRemoteKeyDao(): RemoteKeyDao
}