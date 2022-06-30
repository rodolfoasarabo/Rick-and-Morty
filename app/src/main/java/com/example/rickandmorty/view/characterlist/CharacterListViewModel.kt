package com.example.rickandmorty.view.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.model.FilterModel
import com.example.rickandmorty.data.repository.CharacterListRepository
import com.example.rickandmorty.mapper.CharacterModelToUiModelMapper
import com.example.rickandmorty.usecase.CharacterSource
import com.example.rickandmorty.view.uimodel.CharacterUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterListRepository: CharacterListRepository,
    private val mapper: CharacterModelToUiModelMapper,
) : ViewModel() {

    val characterFlow = MutableStateFlow<CharacterUiModel?>(null)

    val filter = MutableStateFlow<FilterModel?>(null)

    val characters: Flow<PagingData<CharacterUiModel>> =
        Pager(PagingConfig(20), initialKey = 1) {
            CharacterSource(
                characterListRepository,
                mapper,
                filter.value
            )
        }.flow.cachedIn(viewModelScope)
}