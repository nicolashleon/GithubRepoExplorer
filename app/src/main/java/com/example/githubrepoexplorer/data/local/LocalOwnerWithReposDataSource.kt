package com.example.githubrepoexplorer.data.local

import com.example.githubrepoexplorer.ui.models.OwnerWithRepositories
import com.example.githubrepoexplorer.ui.models.RemoteKey

class LocalOwnerWithReposDataSource(private val database: RepositoryDatabase) {

    private val repoDao = database.getRepositoryDao()
    private val ownerDao = database.getOwnerDao()
    private val ownerWithRepositoryDao = database.getOwnerWithRepositoriesDao()
    private val remoteKeyDao = database.getRemoteKeyDao()

    fun getRemoteKeyForRepositories(id: Int) = remoteKeyDao.getRemoteKeyById(id)

    fun getRepoWithOwner(repoId: Long) = ownerWithRepositoryDao.getRepoWithOwner(repoId)

    fun getOwnerWithRepositories(username: String) = ownerWithRepositoryDao.getOwnerWithRepositories(username)

    fun insertOwnerWithRepositories(remoteKey: RemoteKey, ownerWithRepositories: OwnerWithRepositories) {
        database.runInTransaction {
            ownerDao.insertAll(ownerWithRepositories.owner)
            repoDao.insertAll(*ownerWithRepositories.repos.toTypedArray())
            remoteKeyDao.updateRemoteKey(remoteKey)
        }
    }

     fun clearAndInsertRepositoriesAndOwners(remoteKey: RemoteKey, ownerWithRepositories: OwnerWithRepositories) {
        database.runInTransaction {
            remoteKeyDao.delete(remoteKey)
            ownerDao.deleteAll()
            repoDao.deleteAll()
            ownerDao.insertAll(ownerWithRepositories.owner)
            repoDao.insertAll(*ownerWithRepositories.repos.toTypedArray())
            remoteKeyDao.insertRemoteKey(remoteKey)
        }
    }
}