package com.example.rickandmorty.usecase

import com.example.rickandmorty.data.repository.EpisodeDetailRepository
import com.example.rickandmorty.mapper.EpisodeModelToEpisodeDetailUiModelMapper
import com.example.rickandmorty.view.uimodel.EpisodeDetailUiModel
import javax.inject.Inject

interface GetEpisodeById {

    suspend operator fun invoke(episodeId: String): EpisodeDetailUiModel
}

class GetEpisodeByIdUseCase @Inject constructor(
    private val episodeDetailRepository: EpisodeDetailRepository,
    private val mapper: EpisodeModelToEpisodeDetailUiModelMapper
) : GetEpisodeById {

    override suspend operator fun invoke(episodeId: String) =
        mapper.mapFrom(episodeDetailRepository.getEpisodeById(episodeId))
}