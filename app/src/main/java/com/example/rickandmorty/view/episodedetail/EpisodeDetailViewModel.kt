package com.example.rickandmorty.view.episodedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.usecase.GetEpisodeById
import com.example.rickandmorty.view.uimodel.CharacterUiModel
import com.example.rickandmorty.view.uimodel.EpisodeDetailUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val getEpisodeById: GetEpisodeById
) : ViewModel() {

    private val _uiState = MutableStateFlow<EpisodeDetailViewState>(
        EpisodeDetailViewState.Loading
    )

    val uiState: StateFlow<EpisodeDetailViewState> = _uiState

    val characterFlow = MutableStateFlow<CharacterUiModel?>(null)

    fun getEpisode(episodeId: String) {
        viewModelScope.launch {
            val episode = getEpisodeById(episodeId)

            _uiState.value = EpisodeDetailViewState.Success(episode)
        }
    }

}

sealed class EpisodeDetailViewState {
    data object Loading : EpisodeDetailViewState()
    class Success(val episodeDetailUiModel: EpisodeDetailUiModel) : EpisodeDetailViewState()
}