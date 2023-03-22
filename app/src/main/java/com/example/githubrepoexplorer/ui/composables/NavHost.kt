package com.example.githubrepoexplorer.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink

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
            RepositoryListScreen {
                println("NAVIGATE")
                navController.navigate(Destinations.REPOSITORY_DETAILS)
            }
        }
        composable(Destinations.REPOSITORY_DETAILS) {
            RepositoryDetailScreen({
                navController.navigateUp()
            })
        }
    }
}

object Destinations {
    internal const val REPOSITORY_LIST = "repositoryList"
    internal const val REPOSITORY_DETAILS = "repositoryDetail"
}
