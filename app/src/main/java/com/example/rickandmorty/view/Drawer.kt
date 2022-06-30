package com.example.rickandmorty.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R

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
            .padding(start = 24.dp, end = 24.dp)
    ) {
        Image(
            modifier = Modifier
                .height(88.dp)
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.rick_and_morty_logo),
            contentScale = ContentScale.Fit,
            contentDescription = null
        )
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