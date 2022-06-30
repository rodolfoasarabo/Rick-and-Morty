package com.example.rickandmorty.view.episodelist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
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
        onClick = { navController.navigate("episode_detail/${episode.id}") },
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Text(
                    text = episode.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        start = 4.dp,
                        top = 4.dp,
                        end = 4.dp
                    )
                )
                Text(
                    text = episode.airDate,
                    fontSize = 8.sp,
                    modifier = Modifier
                        .padding(bottom = 4.dp, end = 4.dp)
                        .align(
                            Alignment.BottomEnd
                        )
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun EpisodeList(
    viewModel: EpisodeListViewModel = hiltViewModel(),
    navController: NavController,
    openDrawer: () -> Unit
) {
    val episodeList = viewModel.episodes.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = {
                Text(
                    text = "Episode List",
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            },
            backgroundColor = Color.White,
            navigationIcon = {
                Row {
                    Spacer(modifier = Modifier.width(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_menu),
                        colorFilter = ColorFilter.tint(Color.Gray),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable {
                                openDrawer()
                            }
                    )
                }
            }
        )

        LazyVerticalGrid(
            cells = GridCells.Fixed(2)
        ) {
            items(
                count = episodeList.itemCount
            ) { index ->
                episodeList[index]?.let {
                    EpisodeThumbView(episode = it, navController = navController)
                }
            }
        }
        episodeList.apply {
            when {
                loadState.refresh is LoadState.Loading -> LoadingView()
            }
        }
    }
}
