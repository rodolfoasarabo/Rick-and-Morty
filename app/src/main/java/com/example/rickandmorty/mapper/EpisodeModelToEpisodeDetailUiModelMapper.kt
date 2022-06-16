package com.example.rickandmorty.mapper

import com.example.rickandmorty.data.model.CharacterLocationModel
import com.example.rickandmorty.data.model.CharacterModel
import com.example.rickandmorty.data.model.CharacterOriginModel
import com.example.rickandmorty.data.model.EpisodeModel
import com.example.rickandmorty.view.uimodel.CharacterLocationUiModel
import com.example.rickandmorty.view.uimodel.CharacterOriginUiModel
import com.example.rickandmorty.view.uimodel.CharacterUiModel
import com.example.rickandmorty.view.uimodel.EpisodeDetailUiModel
import javax.inject.Inject

interface EpisodeModelToEpisodeDetailUiModelMapper {

    fun mapFrom(from: EpisodeModel): EpisodeDetailUiModel
}

class EpisodeModelToEpisodeDetailUiModelMapperImpl @Inject constructor() :
    EpisodeModelToEpisodeDetailUiModelMapper {

    override fun mapFrom(from: EpisodeModel) =
        EpisodeDetailUiModel(
            from.id,
            from.name,
            from.airDate,
            from.episode,
            from.characters.map {
                mapCharacter(it)
            }
        )

    private fun mapCharacter(from: CharacterModel) =
        CharacterUiModel(
            id = from.id,
            name = from.name,
            status = from.status,
            species = from.species,
            type = from.type,
            gender = from.gender,
            origin = mapOrigin(from.origin),
            location = mapLocation(from.location),
            image = from.image,
            episode = from.episode
        )

    private fun mapOrigin(from: CharacterOriginModel) =
        CharacterOriginUiModel(
            from.name,
            from.url
        )

    private fun mapLocation(from: CharacterLocationModel) =
        CharacterLocationUiModel(
            from.name,
            from.url
        )
}