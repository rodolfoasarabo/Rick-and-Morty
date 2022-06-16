package com.example.rickandmorty.mapper

import com.example.rickandmorty.data.model.EpisodeModel
import com.example.rickandmorty.view.uimodel.EpisodeDetailUiModel
import com.example.rickandmorty.view.uimodel.EpisodeUiModel
import javax.inject.Inject

interface EpisodeModelToUiModelMapper {

    fun mapFrom(from: EpisodeModel): EpisodeUiModel
}

class EpisodeModelToUiModelMapperImpl @Inject constructor() : EpisodeModelToUiModelMapper {

    override fun mapFrom(from: EpisodeModel) =
        EpisodeUiModel(
            from.id,
            from.name,
            from.airDate,
            from.episode
        )
}