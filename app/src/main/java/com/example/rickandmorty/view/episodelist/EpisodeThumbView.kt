package com.example.rickandmorty.view.episodelist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rickandmorty.R
import com.example.rickandmorty.view.LoadingView
import com.example.rickandmorty.view.uimodel.EpisodeUiModel

@ExperimentalMaterialApi
@Composable
fun EpisodeThumbView(
    modifier: Modifier = Modifier,
    episode: EpisodeUiModel,
    navController: NavController
) {
    Card(
        elevation = 4.dp,
        border = BorderStroke(1.dp, Color.Gray),
        onClick = { navController.navigate("episode_detail/${episode.id}") },
        modifier = modifier
            .padding(4.dp)
            .width(180.dp)
            .height(170.dp)
    ) {
        Column(modifier = Modifier.background(Color.White)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.rickmorty),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(106.dp)
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .height(106.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black),
                                startY = 0f,
                                endY = 350f
                            )
                        )
                ) {}
                Text(
                    text = episode.episode,
                    fontSize = 14.sp,
                    maxLines = 2,
                    modifier = Modifier
                        .padding(
                            start = 4.dp,
                            top = 2.dp,
                            end = 4.dp
                        )
                        .align(Alignment.BottomEnd),
                    color = Color.White
                )
            }
            Text(
                text = episode.name,
                modifier = Modifier.padding(
                    start = 4.dp,
                    top = 4.dp,
                    end = 4.dp
                )
            )
            Text(
                text = episode.airDate,
                fontSize = 8.sp,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp, bottom = 4.dp)
            )
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun EpisodeList(
    viewModel: EpisodeListViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState = viewModel.uiStateFlow.collectAsState()

    Column {
        Text(
            text = "Episode List",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Divider()
        when (uiState.value) {
            is EpisodeListViewState.Success -> {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                ) {
                    items((uiState.value as? EpisodeListViewState.Success)?.episodeList.orEmpty()) { episode ->
                        EpisodeThumbView(episode = episode, navController = navController)
                    }
                }
            }
            EpisodeListViewState.Loading -> LoadingView()
        }
    }
}
