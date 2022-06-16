package com.example.rickandmorty.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmorty.view.episodedetail.EpisodeDetailScreen
import com.example.rickandmorty.view.episodelist.EpisodeList
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity @Inject constructor() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "episode_list") {
                    composable("episode_list") {
                        EpisodeList(navController = navController)
                    }
                    composable(
                        "episode_detail/{episodeId}"
                    ) { backStackEntry ->
                        EpisodeDetailScreen(
                            episodeId = backStackEntry.arguments?.getString("episodeId"),
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}