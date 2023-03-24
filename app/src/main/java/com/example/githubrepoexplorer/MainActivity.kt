package com.example.githubrepoexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.githubrepoexplorer.ui.composables.RepositoryExplorerNavHost

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RepositoryExplorerNavHost(navController = rememberNavController())
        }
    }
}



