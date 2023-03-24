package com.example.githubrepoexplorer.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun RepositoryExplorerNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.REPOSITORY_LIST
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.REPOSITORY_LIST) {
            RepositoryListScreen { repoId ->
                navController.navigate(Destinations.REPOSITORY_DETAILS + "/$repoId")
            }
        }
        composable(
            Destinations.REPOSITORY_DETAILS + "/{repoId}",
            arguments = listOf(navArgument("repoId") { type = NavType.LongType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getLong("repoId")?.let {
                RepositoryDetailScreen(it) {
                    navController.navigateUp()
                }
            }
        }
    }
}

object Destinations {
    internal const val REPOSITORY_LIST = "repositoryList"
    internal const val REPOSITORY_DETAILS = "repositoryDetail"
}
