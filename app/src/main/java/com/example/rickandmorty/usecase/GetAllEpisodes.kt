package com.example.rickandmorty.usecase

import com.example.rickandmorty.EpisodeListRepository
import com.example.rickandmorty.mapper.EpisodeModelToUiModelMapper
import com.example.rickandmorty.view.uimodel.EpisodeUiModel
import javax.inject.Inject

interface GetAllEpisodes {
    suspend operator fun invoke(): List<EpisodeUiModel>
}

class GetAllEpisodesUseCase @Inject constructor(
    private val episodeListRepository: EpisodeListRepository,
    private val mapper: EpisodeModelToUiModelMapper
) : GetAllEpisodes {

    override suspend operator fun invoke(): List<EpisodeUiModel> {
        return episodeListRepository.getAllEpisodes().map {
            mapper.mapFrom(it)
        }
    }
}