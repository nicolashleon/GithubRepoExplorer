package com.example.githubrepoexplorer.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubrepoexplorer.ui.models.Owner
import com.example.githubrepoexplorer.ui.models.Repository

@Dao
interface OwnerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg repos: Owner)

    @Query("DELETE FROM owner")
    fun deleteAll()
}
