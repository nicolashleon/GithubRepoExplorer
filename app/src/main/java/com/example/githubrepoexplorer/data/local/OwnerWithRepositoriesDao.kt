package com.example.githubrepoexplorer.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.example.githubrepoexplorer.ui.models.OwnerRepoTuple

@Dao
interface OwnerWithRepositoriesDao {
    @Query(
        "SELECT owner.id AS ownerId, " +
                "owner.login AS ownerLogin, " +
                "owner.image AS ownerImage, " +
                "repository.id AS id, " +
                "repository.name AS name, " +
                "repository.full_name AS fullName, " +
                "repository.description AS description, " +
                "repository.visibility AS visibility, " +
                "repository.private AS isPrivate, " +
                "repository.html_url AS htmlUrl " +
                "FROM owner, repository " +
                "WHERE ownerId=repository.owner_id " +
                "AND ownerLogin=:username"
    )
    fun getOwnerWithRepositories(username: String): PagingSource<Int, OwnerRepoTuple>

    @Query(
        "SELECT owner.id AS ownerId, " +
                "owner.login AS ownerLogin, " +
                "owner.image AS ownerImage, " +
                "repository.id AS id, " +
                "repository.name AS name, " +
                "repository.full_name AS fullName, " +
                "repository.description AS description, " +
                "repository.visibility AS visibility, " +
                "repository.private AS isPrivate, " +
                "repository.html_url AS htmlUrl " +
                "FROM owner, repository " +
                "WHERE ownerId=repository.owner_id " +
                "AND repository.id=:repoId"
    )
    fun getRepoWithOwner(repoId: Long): OwnerRepoTuple


}
