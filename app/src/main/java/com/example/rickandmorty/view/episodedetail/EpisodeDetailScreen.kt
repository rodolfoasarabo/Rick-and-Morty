package com.example.rickandmorty.view.episodedetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
import com.example.rickandmorty.view.uimodel.CharacterUiModel
import com.example.rickandmorty.view.uimodel.EpisodeDetailUiModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun EpisodeDetailScreen(
    viewModel: EpisodeDetailViewModel = hiltViewModel(),
    episodeId: String?,
    navController: NavController
) {
    viewModel.getEpisode(episodeId.orEmpty())
    val uiState = viewModel.uiState.collectAsState()
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    when (uiState.value) {
        EpisodeDetailViewState.Loading -> LoadingView()
        is EpisodeDetailViewState.Success -> {
            (uiState.value as? EpisodeDetailViewState.Success)?.episodeDetailUiModel?.let {
                CharacterBottomSheet(
                    sheetState = bottomSheetState,
                    episodeUiModel = it,
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }
    }
}


@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CharacterBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: ModalBottomSheetState,
    episodeUiModel: EpisodeDetailUiModel,
    viewModel: EpisodeDetailViewModel,
    navController: NavController
) {
    val selectedCharacter = viewModel.characterFlow.collectAsState()
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            BottomSheetCharacterContent(
                character = selectedCharacter.value
            )
        }
    ) {
        EpisodeView(
            modifier = modifier,
            episodeUiModel = episodeUiModel,
            viewModel = viewModel,
            sheetState = sheetState,
            navController = navController
        )
    }
}

@Composable
fun BottomSheetCharacterContent(
    modifier: Modifier = Modifier,
    character: CharacterUiModel?
) {
    Column(modifier = modifier.wrapContentHeight()) {
        Spacer(modifier = Modifier.height(1.dp))
        character?.let { characterSafe ->
            with(characterSafe) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        GlideImage(
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(72.dp)
                                .height(72.dp)
                                .clip(CircleShape),
                            placeHolder = painterResource(id = R.drawable.rickmorty),
                            imageModel = image
                        )
                        Column(
                            modifier = Modifier
                                .height(72.dp)
                                .padding(start = 8.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = name,
                                maxLines = 2,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                val color = when (status) {
                                    "Alive" -> Color.Green
                                    "Dead" -> Color.Red
                                    else -> Color.Gray
                                }
                                Image(
                                    modifier = Modifier.size(12.dp),
                                    colorFilter = ColorFilter.tint(color),
                                    painter = painterResource(id = R.drawable.dot),
                                    contentDescription = "",
                                )
                                Text(
                                    modifier = Modifier.padding(start = 4.dp),
                                    fontSize = 14.sp,
                                    text = "$status · $species"
                                )
                            }
                        }
                    }
                    Divider()
                    Column(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            fontSize = 14.sp,
                            text = "Origin: ${origin.name}"
                        )
                        Text(
                            fontSize = 14.sp,
                            text = "Current location: ${location.name}"
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun EpisodeView(
    modifier: Modifier = Modifier,
    episodeUiModel: EpisodeDetailUiModel,
    viewModel: EpisodeDetailViewModel,
    sheetState: ModalBottomSheetState,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.rickmorty),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(222.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(222.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 0f,
                            endY = 650f
                        )
                    )
            ) {}
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "Back",
                alignment = Alignment.TopStart,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { navController.popBackStack() },
            )
        }
        Text(
            text = episodeUiModel.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
        )
        Text(
            text = episodeUiModel.episode,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        Divider(
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
        )
        Text(
            text = "Characters",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(4),
            modifier = Modifier.padding(8.dp)
        ) {
            items(episodeUiModel.characters) { character ->
                CharacterView(characterUiModel = character) {
                    viewModel.characterFlow.value = it
                    coroutineScope.launch {
                        sheetState.show()
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterView(
    modifier: Modifier = Modifier,
    characterUiModel: CharacterUiModel,
    onCharacterClick: (CharacterUiModel) -> Unit
) {
    Column(
        modifier = modifier
            .width(72.dp)
            .padding(8.dp)
            .clickable {
                onCharacterClick(characterUiModel)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(72.dp)
                .height(72.dp)
                .clip(CircleShape),
            placeHolder = painterResource(id = R.drawable.rickmorty),
            imageModel = characterUiModel.image
        )
        Text(
            text = characterUiModel.name,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp),
            textAlign = TextAlign.Center,
            maxLines = 2
        )
    }
}
