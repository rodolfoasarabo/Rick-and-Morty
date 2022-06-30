package com.example.rickandmorty.view

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.base.LightColors
import com.example.rickandmorty.view.characterlist.CharacterListScreen
import com.example.rickandmorty.view.episodedetail.EpisodeDetailScreen
import com.example.rickandmorty.view.episodelist.EpisodeList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity @Inject constructor() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            MaterialTheme(colors = LightColors) {
                AppMainScreen()
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun AppMainScreen() {
    val navController = rememberNavController()
    Surface {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val openDrawer: () -> Unit = {
            scope.launch {
                drawerState.open()
            }
        }
        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                Drawer(
                    onDestinationClick = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
            }
        ) {
            NavHost(navController = navController, startDestination = "episode_list") {
                composable("episode_list") {
                    EpisodeList(navController = navController, openDrawer = openDrawer)
                }
                composable("character_list") {
                    CharacterListScreen(
                        openDrawer = openDrawer
                    )
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