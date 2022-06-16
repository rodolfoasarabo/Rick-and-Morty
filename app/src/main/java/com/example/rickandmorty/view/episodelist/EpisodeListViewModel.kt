package com.example.rickandmorty.view.episodelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.usecase.GetAllEpisodes
import com.example.rickandmorty.view.uimodel.EpisodeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeListViewModel @Inject constructor(
    private val getAllEpisodes: GetAllEpisodes
) : ViewModel() {

    private val uiState = MutableStateFlow<EpisodeListViewState>(
        EpisodeListViewState.Loading
    )
    val uiStateFlow: StateFlow<EpisodeListViewState> = uiState

    init {
        viewModelScope.launch {
            val episodes = getAllEpisodes()

            if (episodes.isNotEmpty()) {
                uiState.value = EpisodeListViewState.Success(episodes)
            }
        }
    }
}

sealed class EpisodeListViewState {
    object Loading : EpisodeListViewState()
    class Success(val episodeList: List<EpisodeUiModel>) : EpisodeListViewState()
}