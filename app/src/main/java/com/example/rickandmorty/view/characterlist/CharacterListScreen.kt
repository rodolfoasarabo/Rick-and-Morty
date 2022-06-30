@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)

package com.example.rickandmorty.view.characterlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmorty.R
import com.example.rickandmorty.view.BottomSheetCharacterContent
import com.example.rickandmorty.view.BottomSheetFilterContent
import com.example.rickandmorty.view.CharacterView
import com.example.rickandmorty.view.LoadingView
import com.example.rickandmorty.view.uimodel.CharacterUiModel
import kotlinx.coroutines.launch

private const val CHARACTER_DETAILS_BOTTOM_SHEET = 0
private const val FILTER_BOTTOM_SHEET = 1

@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel = hiltViewModel(),
    openDrawer: () -> Unit
) {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val selectedCharacter = viewModel.characterFlow.collectAsState()
    val currentFilter = viewModel.filter.collectAsState()
    val selectedBottomSheet = remember {
        mutableStateOf(0)
    }
    val coroutineScope = rememberCoroutineScope()

    val characterList = viewModel.characters.collectAsLazyPagingItems()
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Character List",
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
            },
            actions = {
                Image(
                    painter = painterResource(id = R.drawable.ic_filter),
                    colorFilter = ColorFilter.tint(Color.Gray),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable {
                            selectedBottomSheet.value = FILTER_BOTTOM_SHEET
                            coroutineScope.launch {
                                bottomSheetState.show()
                            }
                        }
                )
            }
        )
        Box {
            ModalBottomSheetLayout(
                sheetState = bottomSheetState,
                sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                sheetContent = {
                    when (selectedBottomSheet.value) {
                        CHARACTER_DETAILS_BOTTOM_SHEET -> BottomSheetCharacterContent(
                            character = selectedCharacter.value
                        )
                        FILTER_BOTTOM_SHEET -> BottomSheetFilterContent {
                            viewModel.filter.value = it
                            coroutineScope.launch {
                                characterList.refresh()
                                bottomSheetState.hide()
                            }
                        }
                    }
                }
            ) {
                CharacterListView(
                    characterList,
                ) {
                    viewModel.characterFlow.value = it
                    selectedBottomSheet.value = CHARACTER_DETAILS_BOTTOM_SHEET
                    coroutineScope.launch {
                        bottomSheetState.show()
                    }
                }
            }
            if (currentFilter.value != null) {
                FloatingActionButton(
                    onClick = {
                        viewModel.filter.value = null
                        coroutineScope.launch {
                            characterList.refresh()
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    Image(
                        colorFilter = ColorFilter.tint(Color.White),
                        painter = painterResource(id = R.drawable.ic_clear),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterListView(
    characterList: LazyPagingItems<CharacterUiModel>,
    onCharacterClick: (CharacterUiModel) -> Unit
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(4)
    ) {
        items(
            count = characterList.itemCount
        ) { index ->
            characterList[index]?.let { character ->
                CharacterView(characterUiModel = character) {
                    onCharacterClick(it)
                }
            }
        }
    }

    characterList.apply {
        when {
            loadState.refresh is LoadState.Loading -> LoadingView()
        }
    }
}