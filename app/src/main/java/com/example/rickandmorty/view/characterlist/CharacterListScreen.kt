@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)

package com.example.rickandmorty.view.characterlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.rickandmorty.R
import com.example.rickandmorty.view.BottomSheetCharacterContent
import com.example.rickandmorty.view.CharacterView
import com.example.rickandmorty.view.LoadingView
import kotlinx.coroutines.launch

@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel = hiltViewModel(),
    openDrawer: () -> Unit
) {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val selectedCharacter = viewModel.characterFlow.collectAsState()
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
                Image(
                    painter = painterResource(id = R.drawable.ic_menu),
                    colorFilter = ColorFilter.tint(Color.Gray),
                    contentDescription = "",
                    modifier = Modifier
                        .clickable {
                            openDrawer()
                        }
                        .padding(start = 16.dp)
                )
            },
            actions = {
                Image(
                    painter = painterResource(id = R.drawable.ic_filter),
                    colorFilter = ColorFilter.tint(Color.Gray),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 16.dp)
                )
            }
        )
        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetContent = {

                BottomSheetCharacterContent(
                    character = selectedCharacter.value
                )
            }
        ) {
            CharacterListView(
                viewModel,
                bottomSheetState
            )
        }
    }
}

@Composable
fun CharacterListView(
    viewModel: CharacterListViewModel,
    sheetState: ModalBottomSheetState
) {
    val characterList = viewModel.characters.collectAsLazyPagingItems()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn {
        items(characterList) {

        }
    }

    LazyVerticalGrid(
        cells = GridCells.Fixed(4)
    ) {
        items(
            count = characterList.itemCount
        ) { index ->
            characterList[index]?.let { character ->
                CharacterView(characterUiModel = character) {
                    viewModel.characterFlow.value = it
                    coroutineScope.launch {
                        sheetState.show()
                    }
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