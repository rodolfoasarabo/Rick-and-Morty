package com.example.rickandmorty.mapper

import com.example.rickandmorty.data.model.EpisodeModel
import com.example.rickandmorty.view.uimodel.EpisodeDetailUiModel
import javax.inject.Inject

interface EpisodeModelToEpisodeDetailUiModelMapper {

    fun mapFrom(from: EpisodeModel): EpisodeDetailUiModel
}

class EpisodeModelToEpisodeDetailUiModelMapperImpl @Inject constructor(
    private val characterModelToUiModelMapper: CharacterModelToUiModelMapper
) : EpisodeModelToEpisodeDetailUiModelMapper {

    override fun mapFrom(from: EpisodeModel) =
        EpisodeDetailUiModel(
            from.id,
            from.name,
            from.airDate,
            from.episode,
            from.characters.map {
                characterModelToUiModelMapper.mapFrom(it)
            }
        )
}