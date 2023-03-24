package com.example.githubrepoexplorer.data.network

import com.example.githubrepoexplorer.logError
import com.example.githubrepoexplorer.ui.models.OwnerWithRepositories
import com.example.githubrepoexplorer.ui.models.Repository
import com.example.githubrepoexplorer.ui.models.Visibility

class RemoteOwnerWithReposDataSource(private val service: RepositoryService) {

    suspend fun getRepositories(user: String, page: Int, resultsPerPage: Int): Result<OwnerWithRepositories?> {
        return try {
            val response = service.getRepositoriesForUser(user, page, resultsPerPage)
            val repos = response.map {
                Repository(
                    it.id,
                    it.name,
                    it.fullName,
                    it.description.orEmpty(),
                    Visibility.fromApiName(it.visibility),
                    it.private,
                    it.htmlUrl,
                    it.owner.id
                )
            }
            if(repos.isEmpty()) {
                return Result.success(null)
            }

            val ownerToMap = response.firstOrNull()?.owner
            if (ownerToMap != null) {
                val owner = com.example.githubrepoexplorer.ui.models.Owner(
                    ownerToMap.id,
                    ownerToMap.login,
                    ownerToMap.avatarUrl
                )
                Result.success(OwnerWithRepositories(owner, repos))
            } else {
                val throwable = Exception("Owner information is missing")
                logError("Error when fetching the repositories", throwable)
                Result.failure(throwable)
            }

        } catch (e: Throwable) {
            logError("Error when fetching the repositories from the service", e)
            Result.failure(e)
        }
    }

}