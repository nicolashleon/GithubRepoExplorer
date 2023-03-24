package com.example.githubrepoexplorer.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.githubrepoexplorer.ui.models.RemoteKey

@Dao
interface RemoteKeyDao {
    @Query("SELECT * From remotekey WHERE id=:id")
    fun getRemoteKeyById(id: Int): RemoteKey?

    @Insert
    fun insertRemoteKey(remoteKey: RemoteKey)

    @Update
    fun updateRemoteKey(remoteKey: RemoteKey)

    @Delete
    fun delete(remoteKey: RemoteKey)
}