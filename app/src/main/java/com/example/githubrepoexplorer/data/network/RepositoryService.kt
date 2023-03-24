package com.example.githubrepoexplorer.data.network

import com.example.githubrepoexplorer.data.network.models.Repository
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoryService {
    @GET("/users/{user}/repos")
    suspend fun getRepositoriesForUser(
        @Path("user") user: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") resultsPerPage: Int
    ): List<Repository>
}