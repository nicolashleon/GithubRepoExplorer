package com.example.githubrepoexplorer.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubrepoexplorer.ui.models.Repository

@Dao
interface RepositoryDao {
    @Query("SELECT * FROM repository")
    fun getAll(): List<Repository>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg repos: Repository)

    @Query("DELETE FROM repository")
    fun deleteAll()
}
