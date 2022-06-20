package com.example.rickandmorty.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

sealed class DrawerScreens(val title: String, val route: String) {
    object Episodes : DrawerScreens("Episode List", "episode_list")
    object Characters : DrawerScreens("Character List", "character_list")
}

private val screens = listOf(
    DrawerScreens.Episodes,
    DrawerScreens.Characters
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClick: (route: String) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {
        screens.forEach { screen ->
            Text(
                text = screen.title,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.clickable {
                    onDestinationClick(screen.route)
                }
            )
        }
    }
}