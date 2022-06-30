package com.example.rickandmorty.view.episodelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.repository.EpisodeListRepository
import com.example.rickandmorty.mapper.EpisodeModelToUiModelMapper
import com.example.rickandmorty.usecase.EpisodeSource
import com.example.rickandmorty.view.uimodel.EpisodeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class EpisodeListViewModel @Inject constructor(
    private val episodeListRepository: EpisodeListRepository,
    private val mapper: EpisodeModelToUiModelMapper
) : ViewModel() {

    val episodes: Flow<PagingData<EpisodeUiModel>> = Pager(PagingConfig(20)) {
        EpisodeSource(
            episodeListRepository,
            mapper
        )
    }.flow.cachedIn(viewModelScope)
}